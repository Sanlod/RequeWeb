package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.ViewNavigator;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

@Route("register")
@PageTitle("Register - Website")
public class RegisterView extends VerticalLayout {



    private final ViewNavigator navigator;
    public RegisterView(ViewNavigator navigator) {

        this.navigator = navigator;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        VerticalLayout mainContainer = new VerticalLayout();
        mainContainer.setWidth("586px");
        mainContainer.setHeight("511px");
        mainContainer.getStyle()
                .set("background-color", "white")
                .set("border", "1px solid black")
                .set("border-radius", "5px")
                .set("padding", "20px");

        H1 title = new H1("Website");
        title.getStyle().set("color", "#354eab");

        Span subtitle = new Span("Login");
        subtitle.getStyle()
                .set("color", "#a6a6a6")
                .set("font-size", "11px");

        H2 registerTitle = new H2("Create account");

        // Campos del formulario
        TextField nameField = new TextField();
        nameField.setPlaceholder("Username");
        nameField.setWidth("218px");
        nameField.setMinLength(4);

        Span nameError = new Span("Username too short");
        nameError.getStyle()
                .set("color", "red")
                .set("font-size", "11px")
                .set("display", "none");

        EmailField emailField = new EmailField();
        emailField.setPlaceholder("someone@example.com");
        emailField.setWidth("218px");

        Span emailError = new Span("Please enter a valid email address");
        emailError.getStyle()
                .set("color", "red")
                .set("display","none")
                .set("font-size", "11px");

        // Campos de fecha
        HorizontalLayout dateLayout = new HorizontalLayout();
        TextField dayField = new TextField();
        dayField.setPlaceholder("DD");
        dayField.setWidth("41px");

        TextField monthField = new TextField();
        monthField.setPlaceholder("MM");
        monthField.setWidth("41px");

        TextField yearField = new TextField();
        yearField.setPlaceholder("YY");
        yearField.setWidth("41px");

        dateLayout.add(dayField, monthField, yearField);

        // Campo de contraseña con validación
        PasswordField passwordField = new PasswordField();
        passwordField.setPlaceholder("Password");
        passwordField.setWidth("218px");

        Span passwordError = new Span();
        passwordError.getStyle()
                .set("color", "red")
                .set("font-size", "11px")
                .set("display", "none");

        passwordField.addValueChangeListener(e -> {
            String result = validatePassword(e.getValue());
            if (result != null) {
                passwordError.setText(result);
                passwordError.getStyle().set("display", "block");
            } else {
                passwordError.getStyle().set("display", "none");
            }
        });

        // Campo de confirmar contraseña con validación
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPlaceholder("Confirm password");
        confirmPasswordField.setWidth("218px");

        Span confirmPasswordError = new Span();
        confirmPasswordError.getStyle()
                .set("color", "red")
                .set("font-size", "11px")
                .set("display", "none");

        confirmPasswordField.addValueChangeListener(e -> {
            if (!e.getValue().equals(passwordField.getValue())) {
                confirmPasswordError.setText("Passwords do not match.");
                confirmPasswordError.getStyle().set("display", "block");
            } else {
                confirmPasswordError.getStyle().set("display", "none");
            }
        });

        // Términos y condiciones
        Checkbox acceptTerms = new Checkbox("Accept");
        Span termsLink = new Span("Terms and conditions");
        termsLink.getStyle()
                .set("color", "#004aad")
                .set("text-decoration", "underline")
                .set("cursor", "pointer");

        Span acceptTermsError = new Span("Terms and conditions are disabled");
        acceptTermsError.getStyle()
                .set("color", "red")
                .set("display", "none")
                .set("font-size", "11px");

        HorizontalLayout termsLayout = new HorizontalLayout(acceptTerms, termsLink);
        termsLayout.setAlignItems(Alignment.CENTER);

        // Botones
        Button registerButton = new Button("Register", e -> {
            String pwdError = validatePassword(passwordField.getValue());
            boolean passwordsMatch = passwordField.getValue().equals(confirmPasswordField.getValue());
            if(nameField.isInvalid() || nameField.isEmpty()) {
                nameError.getStyle().set("display", "block");
                return;
            }
            else{
                nameError.getStyle().set("display", "none");
            }
            if (emailField.isInvalid() || emailField.isEmpty()) {
                emailError.getStyle().set("display", "block");
                return;
            }
            else{
                emailError.getStyle().set("display", "none");
            }
            if (pwdError != null) {
                passwordError.setText(pwdError);
                passwordError.getStyle().set("display", "block");
                return;
            }
            if (!passwordsMatch) {
                confirmPasswordError.setText("Passwords do not match.");
                confirmPasswordError.getStyle().set("display", "block");
                return;
            }
            if(acceptTerms.getValue() != true) {
                acceptTermsError.getStyle().set("display", "block");
                return;
            }
            else{
                acceptTermsError.getStyle().set("display", "none");
            }

            try {
                guardarData(nameField.getValue(), passwordField.getValue());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            getUI().ifPresent(ui ->
                    ui.navigate(MainView.class,
                            new com.vaadin.flow.router.QueryParameters(
                                    java.util.Map.of("success", java.util.List.of("registered"))
                            )
                    )
            );
        });
        registerButton.getStyle()
                .set("background-color", "#004aad")
                .set("color", "white")
                .set("width", "104px")
                .set("height", "37px");

        Span cancelLink = new Span("Cancel and go back to log in");
        cancelLink.getStyle()
                .set("color", "#004aad")
                .set("text-decoration", "underline")
                .set("cursor", "pointer")
                .set("font-size", "12px");
        cancelLink.addClickListener(e -> navigator.navigateToLogin());

        HorizontalLayout buttonLayout = new HorizontalLayout(registerButton);

        mainContainer.add(
                title, subtitle, registerTitle,
                nameField, nameError ,emailField,emailError, dateLayout,
                passwordField, passwordError,
                confirmPasswordField, confirmPasswordError,
                termsLayout, acceptTermsError,buttonLayout, cancelLink
        );
        mainContainer.setAlignItems(Alignment.START);
        mainContainer.setHeight("auto");

        add(mainContainer);
    }

    private String validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return "Password must be at least 8 characters long, include one uppercase letter and one number.";
        }
        if (!password.chars().anyMatch(Character::isUpperCase)) {
            return "Password must contain at least one uppercase letter.";
        }
        if (!password.chars().anyMatch(Character::isDigit)) {
            return "Password must contain at least one number.";
        }
        return null;
    }

    private void guardarData(String username, String password) throws IOException {
        BufferedWriter boffer = new BufferedWriter(new FileWriter("src/main/resources/loginData")) {};
        boffer.write(username);
        boffer.write("\n");
        boffer.write(password);
        boffer.close();
        System.out.println("Data guardado com sucesso!");
    }
}