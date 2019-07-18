package dev.br.web.collegueapi.exception;

import java.util.HashMap;
import java.util.Map;

public class CollegueInvalideException extends RuntimeException {
    private Map<String,String> erreurs= new HashMap<>();
    public CollegueInvalideException(String message) {
        super(message);
    }

    public CollegueInvalideException(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
}
