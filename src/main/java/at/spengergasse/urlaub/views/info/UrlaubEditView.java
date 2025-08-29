package at.spengergasse.urlaub.views.info;

import at.spengergasse.urlaub.Exception.UrlaubException;
import at.spengergasse.urlaub.domain.Urlaub;
import at.spengergasse.urlaub.service.UrlaubService;
import at.spengergasse.urlaub.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("NEW Urlaub")
@Route(value = "urlaub/edit", layout = MainLayout.class)
@Menu(order = 3, icon = LineAwesomeIconUrl.EDIT)
public class UrlaubEditView extends VerticalLayout {
    private TextField   urlaubId     = new TextField("Urlaubs ID");
    private TextField   land         = new TextField("Land");
    private TextField   ort          = new TextField("Ort");
    private Checkbox    allInclusive = new Checkbox("All Inclusive");
    private NumberField preisPerson  = new NumberField("Preis pro Person");
    private TextField   urlaubArt    = new TextField("Urlaub ART");
    private Button      saveButton;
    private Button      cancelButton;

    public UrlaubEditView(UrlaubService urlaubService) {
        setSpacing(true);
        setPadding(true);

        urlaubId.setEnabled(false);

        FormLayout formLayout = new FormLayout(
                urlaubId,
                land,
                ort,
                allInclusive,
                preisPerson,
                urlaubArt
        );

        saveButton = new Button("Speichern", buttonClickEvent -> {
            try {
                Urlaub urlaub = new Urlaub();
                if (urlaub.getUrlaubId() == null)
                    urlaub.setUrlaubId();
                urlaub.setLand (land.getValue());
                urlaub.setOrt(ort.getValue());
                urlaub.setAllInclusive(allInclusive.getValue());
                urlaub.setPreisPerson(preisPerson.getValue());
                urlaub.setUrlaubArt(urlaubArt.getValue());
                urlaubService.save(urlaub);
            }
            catch (UrlaubException e) {
                Notification.show(e.getMessage());
            }
            catch (Exception e) {
                Notification.show(e.getMessage());
            }
        });

        add(formLayout, saveButton);
    }

}
