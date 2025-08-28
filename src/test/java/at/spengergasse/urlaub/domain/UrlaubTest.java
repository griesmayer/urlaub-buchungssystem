package at.spengergasse.urlaub.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlaubTest {

    @Test
    void testToString() {
        Urlaub u = new Urlaub("Italien", "Rom", false, 1200.0, "Stadt");
        Urlaub u2 = new Urlaub("Österreich", "Wien", false, 1500.0, "Stadt");
        System.out.println(u.getUrlaubId());
        System.out.println(u);
        System.out.println(u2.getUrlaubId());
        System.out.println(u2);
    }
}