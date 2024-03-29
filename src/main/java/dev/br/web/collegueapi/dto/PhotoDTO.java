package dev.br.web.collegueapi.dto;

public class PhotoDTO {
    private String matricule;
    private String photoUrl;

    public PhotoDTO(String matricule, String photoUrl) {
        this.matricule = matricule;
        this.photoUrl = photoUrl;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
