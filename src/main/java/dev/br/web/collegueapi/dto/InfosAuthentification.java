package dev.br.web.collegueapi.dto;

public class InfosAuthentification {
    private String email;

    private String motDePasse;


    public InfosAuthentification(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public InfosAuthentification() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
