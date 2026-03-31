package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.ViewNavigator;

@Route("update-password")
@PageTitle("Update Password - Website")
public class UpdatePasswordView extends VerticalLayout {

    private final ViewNavigator navigator;

    public UpdatePasswordView(ViewNavigator navigator) {
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

        H2 updateTitle = new H2("Update your password");

        Span confirmationText = new Span("We have successfully confirmed your identity, please change your password");
        confirmationText.getStyle()
                .set("color", "#a1a1a1")
                .set("font-size", "12px");

        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPlaceholder("New password");
        newPasswordField.setWidth("218px");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPlaceholder("Confirm password");
        confirmPasswordField.setWidth("218px");

        Button confirmButton = new Button("Confirm", e -> {
            // Lógica para actualizar contraseña
            navigator.navigateToLogin();
        });
        confirmButton.getStyle()
                .set("background-color", "#004aad")
                .set("color", "white")
                .set("width", "104px")
                .set("height", "37px");

        Span cancelLink = new Span("Cancel and go to log in");
        cancelLink.getStyle()
                .set("color", "#004aad")
                .set("text-decoration", "underline")
                .set("cursor", "pointer")
                .set("font-size", "12px");
        cancelLink.addClickListener(e -> navigator.navigateToLogin());

        mainContainer.add(title, subtitle, updateTitle, confirmationText, newPasswordField, confirmPasswordField, confirmButton, cancelLink);
        mainContainer.setAlignItems(Alignment.START);

        add(mainContainer);
    }
}