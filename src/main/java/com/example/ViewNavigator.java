package com.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Service;

@Service
public class ViewNavigator {

    public void navigateToLogin() {
        UI.getCurrent().navigate("");
    }

    public void navigateToRegister() {
        UI.getCurrent().navigate("register");
    }

    public void navigateToVerification() {
        UI.getCurrent().navigate("verification");
    }

    public void navigateToUpdatePassword() {
        UI.getCurrent().navigate("update-password");
    }

    public void navigateToLandingSpot() {
        UI.getCurrent().navigate("landing");
    }

    public void showNotification(String message, String type) {
        Notification notification = new Notification(message, 3000);
        if ("error".equals(type)) {
            notification.setPosition(Notification.Position.MIDDLE);
        }
        notification.open();
    }
}