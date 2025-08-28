package at.spengergasse.urlaub.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlaubServiceTest {

    @Test
    void testToString() {
        UrlaubService us;

        us = new UrlaubService();
        us.fillTestdata(10);
        System.out.println(us);
    }
}