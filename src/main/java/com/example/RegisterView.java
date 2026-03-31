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
        nameField.setPlaceholder("Name");
        nameField.setWidth("218px");

        TextField lastNameField = new TextField();
        lastNameField.setPlaceholder("Last name");
        lastNameField.setWidth("218px");

        EmailField emailField = new EmailField();
        emailField.setPlaceholder("someone@example.com");
        emailField.setWidth("218px");

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

        PasswordField passwordField = new PasswordField();
        passwordField.setPlaceholder("Password");
        passwordField.setWidth("218px");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPlaceholder("Confirm password");
        confirmPasswordField.setWidth("218px");

        // Términos y condiciones
        Checkbox acceptTerms = new Checkbox("Accept");
        Span termsLink = new Span("Terms and conditions");
        termsLink.getStyle()
                .set("color", "#004aad")
                .set("text-decoration", "underline")
                .set("cursor", "pointer");

        HorizontalLayout termsLayout = new HorizontalLayout(acceptTerms, termsLink);
        termsLayout.setAlignItems(Alignment.CENTER);

        // Botones
        Button registerButton = new Button("Register", e -> {
            // Aquí iría la lógica de registro
            navigator.navigateToLogin();
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

        mainContainer.add(title, subtitle, registerTitle, nameField, lastNameField, emailField, dateLayout, passwordField, confirmPasswordField, termsLayout, buttonLayout, cancelLink);
        mainContainer.setAlignItems(Alignment.START);

        add(mainContainer);
    }
}