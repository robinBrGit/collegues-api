package dev.br.web.collegueapi.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Collegue {
    @Id
    private String matricule;
    private String nom;
    private String prenoms;
    @Column(unique = true)
    private String email;
    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;
    private String photoUrl;


    public Collegue() {
    }

    public Collegue(String nom, String prenoms, String email, LocalDate dateDeNaissance, String photoUrl) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.photoUrl = photoUrl;
    }

    public Collegue(String matricule, String nom, String prenoms, String email, LocalDate dateDeNaissance, String photoUrl) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.photoUrl = photoUrl;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public boolean isValid(){
        if(this.nom == null
                || this.prenoms == null
                || this.email == null
                || this.dateDeNaissance == null
                || this.photoUrl == null )return false;
        return true;
    }
}
