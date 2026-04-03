package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;
import com.example.ViewNavigator;

@Route("")
@PageTitle("Login - Website")
public class MainView extends VerticalLayout {

    private final ViewNavigator navigator;

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

        // Botones
        Button loginButton = new Button("Log in", e -> {
            // Aquí iría la lógica de autenticación
            navigator.navigateToLandingSpot();
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
        mainContainer.add(title, subtitle, loginTitle, usernameField, passwordField, forgotPassword, buttonLayout, cuentaCreada);
        mainContainer.setAlignItems(Alignment.START);

        add(mainContainer);
    }

    public boolean habilitarCuentaCreada(){
        return true;
    }
}