# Service

## Service Class 

@Service ist eine Stereotyp-Annotation in Spring (org.springframework.stereotype.Service).
Sie markiert eine Klasse als Service-Komponente (also Teil der Business-Logik).
Spring erkennt diese Klasse beim Component Scanning und registriert sie automatisch als Bean im Application Context.

In einer typischen 3-Schichten-Architektur:
- Controller / View → nimmt HTTP- oder UI-Anfragen entgegen
- Service → enthält die Geschäftslogik
- Repository → kommuniziert mit der Datenbank

```
@Service
public class UrlaubService {
private final ArrayList<Urlaub> data;

    public UrlaubService() {
        this.data = new ArrayList<>();
    }
}
```

@Service macht ZugService zu einer Spring-Bean

Andere Klassen können ihn einfach mit @Autowired oder im Konstruktor verwenden.

## Faker Daten

Die Klasse Faker dient dazu, realistisch aussehende, aber zufällige Testdaten zu generieren.
Perfekt für Demos, Tests oder Mock-Daten in Projekten (z. B. Vaadin-Formulare, Datenbanken, Unit-Tests).

Typische Faker-Kategorien
- Personendaten: name().firstName(), name().lastName(), name().fullName()
- Adressen: address().streetAddress(), address().city(), address().country()
- Internet: internet().emailAddress(), internet().domainName(), internet().ipV4Address()
- Unternehmen: company().name(), company().bs(), company().industry()
- Finanzen: finance().creditCard(), finance().iban()
- Sonstiges: book().title(), beer().name(), color().name()

```
    public void fillTestData(int anz) {
        Urlaub u;
        Faker faker;
        String[] URLAUBART = {"Strand", "Stadt", "Rundreise", "Shopping", "Safari"};

        faker = new Faker();
        data.removeAll(data);
        for (int i=0; i<anz; i++) {
            u = new Urlaub(
                    faker.address().country(),
                    faker.address().cityName(),
                    faker.bool().bool(),
                    faker.number().randomDouble(2, 900, 4000),
                    URLAUBART[faker.number().numberBetween(0, URLAUBART.length)]
            );
            data.add(u);
        }
    }
```

## toString

Wie immer

## Test

```
class UrlaubServiceTest {
    @Test
    public void testToString() {
        UrlaubService liste = new UrlaubService();
        System.out.println(liste.toString());
    }
}
```
