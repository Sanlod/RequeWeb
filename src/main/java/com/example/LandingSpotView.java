package com.example;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.ViewNavigator;

@Route("landing")
@PageTitle("Landing Spot")
public class LandingSpotView extends VerticalLayout {

    private final ViewNavigator navigator;

    public LandingSpotView(ViewNavigator navigator) {
        this.navigator = navigator;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        VerticalLayout mainContainer = new VerticalLayout();
        mainContainer.setAlignItems(Alignment.CENTER);

        H1 title = new H1("Landing spot");

        Span logoutLink = new Span("Cerrar sesion");
        logoutLink.getStyle()
                .set("color", "#4000ff")
                .set("text-decoration", "underline")
                .set("cursor", "pointer");
        logoutLink.addClickListener(e -> navigator.navigateToLogin());

        mainContainer.add(title, logoutLink);
        add(mainContainer);
    }
}