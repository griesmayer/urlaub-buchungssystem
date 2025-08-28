package at.spengergasse.urlaub.views.urlaube;

import at.spengergasse.urlaub.domain.Urlaub;
import at.spengergasse.urlaub.service.UrlaubService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Urlaube")
@Route("urlaube")
@Menu(order = 2, icon = LineAwesomeIconUrl.UMBRELLA_BEACH_SOLID)
public class UrlaubView extends VerticalLayout {
    private final Grid<Urlaub> grid = new Grid<>(Urlaub.class, true);
    private final UrlaubService urlaubService;

    private Button buttonLoscheAlle;
    private Button buttonAdd10;

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

        grid.setSizeFull();
        add(new HorizontalLayout(buttonLoscheAlle, buttonAdd10), grid);
        reload();
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
