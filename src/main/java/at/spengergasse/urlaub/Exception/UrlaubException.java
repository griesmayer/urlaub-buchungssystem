package at.spengergasse.urlaub.Exception;

public class UrlaubException extends RuntimeException {
    public UrlaubException(String meldung) {
        super(meldung);
    }
}
