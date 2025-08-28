package at.spengergasse.urlaub.views.preisliste;

import com.github.javafaker.Faker;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Preisliste")
@Route("preisliste")
@Menu(order = 1, icon = LineAwesomeIconUrl.DOLLAR_SIGN_SOLID)
public class PreislisteView extends VerticalLayout {
    public PreislisteView() {
        H1 titel = new H1("Preisliste");

        Paragraph zusatztext = new Paragraph("inkl. MWST");

        VerticalLayout privat = new VerticalLayout(
                new H3("Privatbuchungen"),
                new H4("50€"),
                new Paragraph(new Faker().lorem().paragraph(10)));
        VerticalLayout gruppe = new VerticalLayout(
                new H3("Gruppenreise"),
                new H4("20€"),
                new Paragraph(new Faker().lorem().paragraph(13)));
        VerticalLayout online = new VerticalLayout(
                new H3("Onlinebuchungen"),
                new H4("15€"),
                new Paragraph(new Faker().lorem().paragraph(8)));
        HorizontalLayout preisliste = new HorizontalLayout(privat, gruppe, online);

        Paragraph danke = new Paragraph("Danke für Ihre Buchung!");

        add(titel, zusatztext, preisliste, danke);
    }
}
