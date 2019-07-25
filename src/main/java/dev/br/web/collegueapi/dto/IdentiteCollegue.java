package dev.br.web.collegueapi.dto;

import java.util.List;

public class IdentiteCollegue {
    private String matricule;
    private String nom;
    private String prenoms;
    private String photoUrl;
    private List<String> roles;

    public IdentiteCollegue(String matricule, String nom, String prenoms, String photoUrl,List<String> roles) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenoms = prenoms;
        this.roles = roles;
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public IdentiteCollegue() {
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
