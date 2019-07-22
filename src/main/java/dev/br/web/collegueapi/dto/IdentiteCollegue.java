package dev.br.web.collegueapi.dto;

import java.util.List;

public class IdentiteCollegue {
    private String matricule;
    private String nom;
    private String prenoms;
    private List<String> roles;

    public IdentiteCollegue(String matricule, String nom, String prenoms, List<String> roles) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenoms = prenoms;
        this.roles = roles;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
