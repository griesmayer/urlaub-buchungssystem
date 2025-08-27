# Buttons

<img src="picture/08_001.png" width=30%>

## Exception

```
public class UrlaubException extends RuntimeException {
    public UrlaubException(String message) {
        super(message);
    }
}
```

Als RuntimeException, da auch Vaadin intern mit RuntimeException arbeitet.

RuntimeException ist eine Basisklasse für viele Ausnahmen in Java.  Sie gehört zur Gruppe der unchecked Exceptions -> d. h. man muss sie nicht explizit mit try-catch behandeln oder in der Methodensignatur mit throws deklarieren.

Tritt meist auf, wenn im Programm etwas logisch Unerwartetes passiert, das man nicht vernünftig abfangen kann (z. B. ein Programmierfehler).

Beispiele:
- NullPointerException: Zugriff auf null
- ArrayIndexOutOfBoundsException: Array-Zugriff außerhalb des Bereichs
- IllegalArgumentException: Methode bekommt ungültigen Parameter
- IllegalStateException: Objektzustand erlaubt die Operation nicht
- ArithmeticException: Division durch 0

## Eigenschaft

```
public class UrlaubListe1  extends VerticalLayout {
    private Button buttonRemoveShopping;
    private Button buttonRemoveNix;
```

Die Komponenten werden als Eigenschaft definiert.

## Hinzufügen und Event

```
    private void init() {
        buttonRemoveShopping = new Button("Lösche alle shopping Urlaube", buttonClickEvent -> removeShopping());
        buttonRemoveNix      = new Button("Lösche alle NIX Urlaube",      buttonClickEvent -> removeNix());
        add(new HorizontalLayout(buttonRemoveAll, buttonAdd10, buttonRemoveShopping, buttonRemoveNix), grid);
    }
```

Die beiden Buttons werden instanziert und dem Layout hinzugefügt.

## Service Klasse

```
    public void removeAll(String urlaubArt) throws UrlaubException {
        Urlaub u;
        Iterator<Urlaub> it;
        int anz;

        anz = 0;
        if (urlaubArt == null)
            throw new UrlaubException("Fehler: keine Urlaubsart angegeben!");
        it = data.iterator();
        while (it.hasNext()) {
            u = it.next();
            if (u.getUrlaubArt().equals(urlaubArt)) {
                it.remove();
                anz++;
            }
        }
        if (anz == 0)
            throw new UrlaubException("Fehler: keinen Urlaub mit der Art " + urlaubArt + " gefunden!");
    }
```

Hier wird die Collection verändert und im Fehlerfall eine Exception geworfen!

## Abarbeitung

```
    private void removeNix() {
        try {
            urlaubService.removeAll("Nix");
            reload();
        }
        catch (UrlaubException e) {
            Notification.show(e.getMessage());
        }
        catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    private void removeShopping() {
        try {
            urlaubService.removeAll("Shopping");
            reload();
        }
        catch (UrlaubException e) {
            Notification.show(e.getMessage());
        }
        catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }
```

Ein einfacher Methodenaufruf der Service Klasse. Auf das Exceptionhandling nicht vergessen. Zur Sicherheit auch die "Exception" abfangen.

<img src="picture/08_002.png" width=30%>
