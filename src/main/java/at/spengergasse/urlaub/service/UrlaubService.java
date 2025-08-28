package at.spengergasse.urlaub.service;

import at.spengergasse.urlaub.Exception.UrlaubException;
import at.spengergasse.urlaub.domain.Urlaub;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class UrlaubService {
    private final ArrayList<Urlaub> data;

    public UrlaubService() {
        data = new ArrayList<>(100);
        fillTestdata(100);
    }

    public void fillTestdata(int anz) {
        Urlaub u;
        Faker faker;

        faker = new Faker();
        for (int i=0; i<anz; i++) {
            u = new Urlaub(
                    faker.address().country(),
                    faker.address().cityName(),
                    faker.bool().bool(),
                    faker.number().randomDouble(2,600, 4000),
                    Urlaub.URLAUBART[faker.number().numberBetween(0, Urlaub.URLAUBART.length)]);
            data.add(u);
        }
    }

    public ArrayList<Urlaub> findAll (){
        ArrayList<Urlaub> copy = new ArrayList<Urlaub>(data);
        return copy;
    }

    @Override
    public String toString() {
        String erg;

        erg = "";
        for (Urlaub u : data) {
            erg += u.toString()+"\n";
        }
        return erg;
    }

    public void loscheAlle() {
        data.removeAll(data);
    }

    public void loscheUrlaubArt(String urlaubsArt) {
        Urlaub u;
        Iterator<Urlaub> it;
        int anz;

        if (urlaubsArt == null)
            throw new UrlaubException("Fehler: keine gültige Urlaubsart!");
        anz = 0;
        it = data.iterator();
        while (it.hasNext()) {
            u = it.next();
            if (u.getUrlaubArt().equals(urlaubsArt)) {
                it.remove();
                anz++;
            }
        }
        if (anz == 0)
            throw new UrlaubException("Fehler: konnte keine Daten löschen!");
    }
}









