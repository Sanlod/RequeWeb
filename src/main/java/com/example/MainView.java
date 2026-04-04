package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@Route("")
@PageTitle("Login - Website")
public class MainView extends VerticalLayout {

    private final ViewNavigator navigator;
    private String username;
    private String password;
    public MainView(ViewNavigator navigator) {
        this.navigator = navigator;
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Contenedor principal
        VerticalLayout mainContainer = new VerticalLayout();
        mainContainer.setWidth("586px");
        mainContainer.setHeight("511px");
        mainContainer.getStyle()
                .set("background-color", "white")
                .set("border", "1px solid black")
                .set("border-radius", "5px")
                .set("padding", "20px");

        // Header
        H1 title = new H1("Website");
        title.getStyle().set("color", "#354eab");

        Span subtitle = new Span("Login");
        subtitle.getStyle()
                .set("color", "#a6a6a6")
                .set("font-size", "11px");

        H2 loginTitle = new H2("Log in");

        // Formulario
        TextField usernameField = new TextField();
        usernameField.setPlaceholder("Email, Phone, Username...");
        usernameField.setWidth("218px");
        usernameField.addValueChangeListener(e -> {

        });

        PasswordField passwordField = new PasswordField();
        passwordField.setPlaceholder("Password");
        passwordField.setWidth("218px");

        // Botón de olvidé contraseña
        Span forgotPassword = new Span("Forgot my password");
        forgotPassword.getStyle()
                .set("color", "#004aad")
                .set("text-decoration", "underline")
                .set("cursor", "pointer")
                .set("font-size", "12px");
        forgotPassword.addClickListener(e -> navigator.navigateToVerification());

        Span errorLogin = new Span("Incorrect username or password");
        errorLogin.getStyle()
                .set("color", "red")
                .set("display", "none")
                .set("font-size", "11px");

        // Botones
        Button loginButton = new Button("Log in", e -> {
            // Aquí iría la lógica de autenticación
        });
        loginButton.addClickListener(e -> {
            try {
                if(checkearDatos(usernameField.getValue(), passwordField.getValue())) {
                    navigator.navigateToLandingSpot();
                }
                else{
                    errorLogin.getStyle().set("display", "block");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        loginButton.getStyle()
                .set("background-color", "#004aad")
                .set("color", "white")
                .set("width", "104px")
                .set("height", "37px");

        Button registerButton = new Button("Register", e -> navigator.navigateToRegister());
        registerButton.getStyle()
                .set("background-color", "#f3f6fa")
                .set("border", "1.5px solid #066df4")
                .set("width", "104px")
                .set("height", "37px");

        // Layout de botones
        HorizontalLayout buttonLayout = new HorizontalLayout(registerButton, loginButton);
        buttonLayout.setSpacing(true);

        Span cuentaCreada = new Span("Cuenta creada satisfactoriamente");
        cuentaCreada.getStyle()
                .set("color", "black")
                .set("font", "11px solid black")
                .set("display", "none");


        // Organizar componentes
        mainContainer.add(title, subtitle, loginTitle, usernameField, passwordField, forgotPassword, buttonLayout, errorLogin,cuentaCreada);
        mainContainer.setAlignItems(Alignment.START);

        add(mainContainer);
    }


    public boolean checkearDatos(String username, String password) throws IOException {
        BufferedReader boffer = new BufferedReader(new FileReader("src/main/resources/loginData")){};
        String usuario = boffer.readLine();
        String contra = boffer.readLine();
        boffer.close();
        System.out.println("Data leida com  sucesso!");
        System.out.println("Usuario: " + usuario);
        System.out.println("Contra: " + contra);
        return usuario.equals(username) && contra.equals(password);
    }
}