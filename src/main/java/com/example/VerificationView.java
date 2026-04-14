package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.ViewNavigator;

@Route("verification")
@PageTitle("Verification - Website")
public class VerificationView extends VerticalLayout {

    private final ViewNavigator navigator;
    public static String currentEmail = "";


    public VerificationView(ViewNavigator navigator) {
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

        H2 verificationTitle = new H2("Update your password");

        EmailField emailField = new EmailField();
        emailField.setPlaceholder("Email");
        emailField.setWidth("218px");
        Span emailError = new Span("Please enter a valid email.");
        emailError.getStyle()
                .set("color", "red")
                .set("font-size", "11px")
                .set("display", "none");

        Button sendButton = new Button("Send", e -> {
            if (emailField.isEmpty() || emailField.isInvalid()) {
                emailError.getStyle().set("display", "block");
            } else {
                emailError.getStyle().set("display", "none");
                currentEmail = emailField.getValue();
                Notification.show("Code sent to " + emailField.getValue());
            }
        });
        sendButton.getStyle()
                .set("background-color", "#004aad")
                .set("color", "white")
                .set("width", "104px")
                .set("height", "37px");

        Span infoText = new Span("Please enter your e-mail and we will send you a verification code");
        Span codeError = new Span("Please enter the verification code.");
        codeError.getStyle()
                .set("color", "red")
                .set("font-size", "11px")
                .set("display", "none");
        infoText.getStyle()
                .set("color", "#acacac")
                .set("font-size", "12px");

        HorizontalLayout emailLayout = new HorizontalLayout(emailField, sendButton, infoText);
        emailLayout.setAlignItems(Alignment.CENTER);
        emailLayout.setSpacing(true);

        TextField verificationCode = new TextField();
        verificationCode.setPlaceholder("Verification code");
        verificationCode.setWidth("218px");

        Button confirmButton = new Button("Confirm", e -> {
            if (verificationCode.isEmpty()) {
                codeError.getStyle().set("display", "block");
                return;
            }
            codeError.getStyle().set("display", "none");
            navigator.navigateToUpdatePassword();
            navigator.navigateToUpdatePassword();
        });
        confirmButton.getStyle()
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

        mainContainer.add(title, subtitle, verificationTitle, emailLayout, emailError, verificationCode, codeError, confirmButton, cancelLink);        mainContainer.setAlignItems(Alignment.START);

        add(mainContainer);
    }
}