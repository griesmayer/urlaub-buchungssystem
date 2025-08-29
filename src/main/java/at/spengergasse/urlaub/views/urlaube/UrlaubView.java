package at.spengergasse.urlaub.views.urlaube;

import at.spengergasse.urlaub.Exception.UrlaubException;
import at.spengergasse.urlaub.domain.Urlaub;
import at.spengergasse.urlaub.service.UrlaubService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Urlaube")
@Route("urlaube")
@Menu(order = 2, icon = LineAwesomeIconUrl.UMBRELLA_BEACH_SOLID)
public class UrlaubView extends VerticalLayout {
    private final Grid<Urlaub> grid = new Grid<>(Urlaub.class, false);
    private final UrlaubService urlaubService;

    private Button buttonLoscheAlle;
    private Button buttonAdd10;
    private Button buttonLoscheStadt;
    private Button buttonLoscheNix;

    public UrlaubView(@Autowired UrlaubService urlaubService) {
        this.urlaubService = urlaubService;
        init();
    }

    private void init() {
        setSizeFull();

        /*
        buttonLoscheAlle = new Button("Lösche ALLE Urlaube");
        buttonLoscheAlle.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                loscheAlle();
            }
        });*/
        buttonLoscheAlle = new Button("Lösche ALLE Urlaube", buttonClickEvent -> loscheAlle());
        buttonAdd10 = new Button("Füge 10 Urlaube hinzu", buttonClickEvent -> add10());

        buttonLoscheStadt = new Button("Lösche ALLE Stadt Urlaube", buttonClickEvent -> loscheUrlaubArt("Stadt"));
        buttonLoscheNix   = new Button("Lösche ALLE NIX Urlaube",   buttonClickEvent -> loscheUrlaubArt("NIX"));

        grid.addColumn(new ValueProvider<Urlaub, Long>() {
                           @Override
                           public Long apply(Urlaub urlaub) {
                               return urlaub.getUrlaubId();
                           }
                       })
                .setHeader("Urlaub ID")
                .setSortable(true);

        grid.addColumn(urlaub -> urlaub.getLand())
                .setHeader("Land")
                .setSortable(true);

        grid.addColumn(Urlaub::getOrt)
                .setHeader("Ort")
                .setSortable(true);

        grid.addColumn(urlaub -> urlaub.getAllInclusive() ? "Y" : "N")
                .setHeader("All Inclusive")
                .setSortable(true);

        grid.addComponentColumn(new ValueProvider<Urlaub, Component>() {
            @Override
            public Component apply(Urlaub urlaub) {
                Checkbox cb;
                cb = new Checkbox(urlaub.getAllInclusive().booleanValue());
                cb.setReadOnly(true);
                return cb;
            }
        })
                .setHeader("All Inclusive");

        Image img2 = new Image("images/dollar.png", "");
        img2.setWidth("20px");
        Span text = new Span("Preis pro Person");
        grid.addColumn(urlaub -> urlaub.getPreisPerson())
                .setHeader(new HorizontalLayout(img2, text))
                .setSortable(true);

        grid.addColumn(urlaub -> urlaub.getUrlaubArt())
                .setHeader("Urlaubs Art")
                .setSortable(true);

        grid.setSizeFull();
        add(new HorizontalLayout(buttonAdd10, buttonLoscheAlle, buttonLoscheStadt, buttonLoscheNix), grid);
        reload();
    }

    private void loscheUrlaubArt(String urlaubsArt) {
        try {
            urlaubService.loscheUrlaubArt(urlaubsArt);
            reload();
        }
        catch (UrlaubException e) {
            Notification.show(e.getMessage());
        }
        catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    private void add10() {
        urlaubService.fillTestdata(10);
        reload();
    }

    private void loscheAlle() {
        urlaubService.loscheAlle();
        reload();
    }

    private void reload() {
        grid.setItems(urlaubService.findAll());
    }
};
