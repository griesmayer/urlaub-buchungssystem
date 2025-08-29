package at.spengergasse.urlaub.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
@Setter
@ToString
public class Urlaub {
    public  static final String[] URLAUBART = {"Strand", "Stadt", "Rundreise", "Shopping", "Safari"};
    private static final AtomicLong sequence = new AtomicLong(1000);
    @Id
    private Long urlaubId;

    @NotNull(message = "Das Land muss angegeben werden!")
    private String  land;
    private String  ort;
    private Boolean allInclusive;
    private Double  preisPerson;
    private String  urlaubArt;

    public Urlaub() {
    }

    public Urlaub(Long urlaubId, String land, String ort, Boolean allInclusive, Double preisPerson, String urlaubArt) {
        setUrlaubId (urlaubId);
        setLand (land);
        setOrt (ort);
        setAllInclusive (allInclusive);
        setPreisPerson (preisPerson);
        setUrlaubArt (urlaubArt);
    }

    public Urlaub(String land, String ort, Boolean allInclusive, Double preisPerson, String urlaubArt) {
        setUrlaubId ();
        setLand (land);
        setOrt (ort);
        setAllInclusive (allInclusive);
        setPreisPerson (preisPerson);
        setUrlaubArt (urlaubArt);
    }

    public void setUrlaubId() {
        this.urlaubId = sequence.getAndIncrement();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Urlaub urlaub = (Urlaub) o;
        return Objects.equals(urlaubId, urlaub.urlaubId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(urlaubId);
    }

    public Urlaub clone() {
        return new Urlaub(urlaubId, land, ort, allInclusive, preisPerson, urlaubArt);
    }
}
