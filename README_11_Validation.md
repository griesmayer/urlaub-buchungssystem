# Validation

Jakarta Bean Validation ist ein Standard in Java, um Eingaben und Objekte zu validieren, ohne dass du die Prüfungen jedes Mal von Hand schreiben musst.

Prüfungen in den Setter-Methoden meiner Klasse geht natürlich auch.

Bei Bean Validation hat einige große Vorteile gegenüber „Validierung in Settern“.

## Vorteile

### Trennung von Datenmodell und Validierungslogik

Setter-Variante:

```
public void setTage(int tage) {
   if (tage < 1 || tage > 30) {
      throw new UrlaubException("tage muss zwischen 1 und 30 liegen");
   }
   this.tage = tage;
}
```

Der Code ist fest in deiner Klasse „eingebrannt“.

Bean Validation:

```
@Min(1) @Max(30)
private int tage;
```

Lesbarer, klarer, deklarativ – und die Logik ist einheitlich.

### Einheitlicher Mechanismus für Frameworks

Bean Validation wird von vielen Frameworks automatisch unterstützt:
- Spring Boot prüft REST-Requests bei @Valid automatisch.
- Vaadin Binder kann die Annotations direkt auswerten.
- Hibernate/JPA prüft Entities beim Persistieren.

Wenn du Validierung nur in den Settern machst, weiß Spring/Vaadin davon nichts → du musst die Logik selbst an jeder Stelle aufrufen.

### Flexibler Umgang mit Validierung

Mit Bean Validation kannst du Gruppen definieren:

```
@NotNull(groups = Create.class)
@Null(groups = Update.class)
private Long id;
```

Damit kannst du sagen: „Beim Erstellen muss id null sein, beim Ändern nicht.“

Mit Settern geht das kaum elegant.

### Bessere Wiederverwendung & Lesbarkeit

Bean Validation ist deklarativ → jeder sieht sofort an der Klasse, welche Regeln gelten.

Settermethoden mit Validierung können unübersichtlich werden.

### Standardisiert & erweiterbar

Jakarta Bean Validation ist ein Standard (JSR 380 / Jakarta EE) → in allen großen Frameworks gleich.

Du kannst eigene Annotations bauen (@UsernameOrEmail, @StrongPassword, …).

Bei Settern müsstest du die Logik jedes Mal neu schreiben.

### Kurz gesagt

Setter-Validierung = geht, aber unflexibel, nicht wiederverwendbar, keine Framework-Unterstützung.

Bean Validation = deklarativ, lesbarer, automatisch von Spring/Vaadin/JPA unterstützt.

Deshalb: Bean Validation ist die bessere Wahl, gerade in Projekten mit Frameworks.

## Grundidee

Du annotierst Felder oder Methoden deiner Klassen mit Constraints wie @NotNull, @Size, @Min, @Email …

Ein Validator prüft dann automatisch, ob diese Regeln erfüllt sind.

Wird eine Regel verletzt, bekommst du eine ConstraintViolation zurück.

Das ist praktisch für:

- Formulareingaben (z. B. Vaadin, JSF, REST-Requests)
- Datenbank-Entities (z. B. mit JPA/Hibernate)
- DTOs (Data Transfer Objects)

## Wichtigsten Constraints 

### Allgemeine Constraints

- @Null → Wert muss null sein
- @NotNull → Wert darf nicht null sein (leerer String oder leere Liste ist aber erlaubt)
- @NotEmpty → String/Collection/Array darf nicht null und nicht leer sein
- @NotBlank → String darf nicht null, leer oder nur Whitespace sein

### String / Collections

- @Size(min, max) → prüft Länge (String) oder Anzahl (Collection, Array, Map)
- @Pattern(regexp, flags) → String muss Regex entsprechen
- @Email → prüft E-Mail-Adresse (einfaches Pattern)

### Zahlen

- @Min(value) → Zahl ≥ value
- @Max(value) → Zahl ≤ value
- @Positive → Zahl > 0
- @PositiveOrZero → Zahl ≥ 0
- @Negative → Zahl < 0
- @NegativeOrZero → Zahl ≤ 0
- @DecimalMin(value, inclusive) → wie @Min, aber für BigDecimal/String
- @DecimalMax(value, inclusive) → wie @Max, aber für BigDecimal/String
- @Digits(integer, fraction) → maximale Anzahl von Stellen vor/nach dem Komma

### Datum/Zeit

- @Past → muss in der Vergangenheit liegen
- @PastOrPresent → muss heute oder früher sein
- @Future → muss in der Zukunft liegen
- @FutureOrPresent → muss heute oder später sein

### Booleans

- @AssertTrue → Wert muss true sein
- @AssertFalse → Wert muss false sein

## Beispiele

```
import jakarta.validation.constraints.*;

public class User {

    @NotBlank(message = "Der Benutzername darf nicht leer sein")
    private String username;

    @Size(min = 8, max = 20, message = "Das Passwort muss zwischen 8 und 20 Zeichen lang sein")
    private String password;

    @Email(message = "Bitte eine gültige E-Mail-Adresse angeben")
    private String email;
}
```