package at.spengergasse.urlaub.views.info;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.hibernate.dialect.function.array.H2ArrayContainsFunction;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Info")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.INFO_SOLID)
public class InfoView extends VerticalLayout {

    public InfoView() {
        setSpacing(true);

        Image img = new Image("images/logo.png", "logo");
        img.setWidth("800px");
        add(img);

        H2 shortText = new H2("Unser Urlaubsbuchungssystem macht die Planung Ihrer Reise so einfach wie noch nie.");
        shortText.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(shortText);

        Paragraph text = new Paragraph("Kunden können bequem aus einer Vielzahl von Reisezielen weltweit auswählen und ihre Wunschreise individuell zusammenstellen. Hotels, Flüge und Zusatzleistungen lassen sich direkt im System kombinieren und sofort buchen. Dank einer transparenten Preisübersicht behalten Sie jederzeit die volle Kostenkontrolle. Alle Angebote werden in Echtzeit angezeigt und die Verfügbarkeiten automatisch aktualisiert. Nach der Buchung erhalten Sie Ihre Reiseunterlagen sofort digital. Über einen persönlichen Bereich können Kunden ihre gebuchten Reisen jederzeit einsehen und verwalten. Bei Fragen oder Änderungswünschen ist das Reisebüro direkt über das System erreichbar. Sichere Bezahlmethoden sorgen für einen reibungslosen und sorgenfreien Buchungsprozess. So wird die Urlaubsplanung zu einem komfortablen, transparenten und stressfreien Erlebnis.");
        text.addClassNames(Margin.Top.XLARGE);
        add(text);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
