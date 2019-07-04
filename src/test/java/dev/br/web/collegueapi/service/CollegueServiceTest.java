package dev.br.web.collegueapi.service;

import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueInvalideException;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class CollegueServiceTest {


    @Test
    public void ajouter_un_collegue_test_nom_invalide() {
        //Test Nom
        CollegueService coll = new CollegueService();
        assertThrows(CollegueInvalideException.class,
                ()-> coll.ajouterUnCollegue(
                        new Collegue(
                                "e",
                                "prenom",
                                "email@",
                                LocalDate.of(1993, 12, 11),
                                "http")));
    }

    @Test
    public void ajouter_un_collegue_test_prenom_invalide(){
        //Test prenom
        CollegueService coll = new CollegueService();
        assertThrows(CollegueInvalideException.class,
                ()-> coll.ajouterUnCollegue(
                        new Collegue(
                                "nom",
                                "p",
                                "email@",
                                LocalDate.of(1993, 12, 11),
                                "http")));
    }

    @Test
    public void ajouter_un_collegue_test_prenom_nom_valide(){
        //Test prenom valide
        CollegueService coll = new CollegueService();
        Collegue collegue = coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.of(1993, 12, 11),
                        "http"));
        assertEquals("prenom",collegue.getPrenoms());
    }
    @Test
    public void ajouter_un_collegue_test_nom_prenom_valide(){
        //Test nom valide
        CollegueService coll = new CollegueService();
        Collegue collegue = coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.of(1993, 12, 11),
                        "http"));
        assertEquals("nom",collegue.getNom());
    }


    @Test
    public void ajouter_un_collegue_test_email_valide() {
        //Test email valide
        CollegueService coll = new CollegueService();
        Collegue collegue = coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.of(1993, 12, 11),
                        "http"));
        assertEquals("email@",collegue.getEmail());
    }

    @Test
    public void ajouter_un_collegue_test_email_invalide_caractere() {
        //Test Email sans @
        CollegueService coll = new CollegueService();
        assertThrows(CollegueInvalideException.class,
                ()-> coll.ajouterUnCollegue(
                        new Collegue(
                                "nom",
                                "prenom",
                                "email",
                                LocalDate.of(1993, 12, 11),
                                "http")));
    }

    @Test
    public void ajouter_un_collegue_test_email_invalide_lenght() {
        //Test Email sans @
        CollegueService coll = new CollegueService();
        assertThrows(CollegueInvalideException.class,
                ()-> coll.ajouterUnCollegue(
                        new Collegue(
                                "nom",
                                "prenom",
                                "e@",
                                LocalDate.of(1993, 12, 11),
                                "http")));
    }
    @Test
    public void ajouter_un_collegue_test_email_invalide_caractere_lenght() {
        //Test Email sans @
        CollegueService coll = new CollegueService();
        assertThrows(CollegueInvalideException.class,
                ()-> coll.ajouterUnCollegue(
                        new Collegue(
                                "nom",
                                "prenom",
                                "e",
                                LocalDate.of(1993, 12, 11),
                                "http")));
    }


    @Test
    public void ajouter_un_collegue_test_photo_url_invalide() {
        //Test photoUrl invalide
        CollegueService coll = new CollegueService();
        assertThrows(CollegueInvalideException.class,
                () -> coll.ajouterUnCollegue(
                        new Collegue(
                                "nom",
                                "prenom",
                                "email@",
                                LocalDate.of(1993, 12, 11),
                                "htt")));
    }

    @Test
    public void ajouter_un_collegue_test_photo_url_valide() {
        //Test photoUrl invalide
        CollegueService coll = new CollegueService();
        Collegue collegue = coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.of(1993, 12, 11),
                        "http"));
        assertEquals("http",collegue.getPhotoUrl());
    }



    @Test
    public void ajouter_un_collegue_test_age_invalide(){
        //Test age invalide
        CollegueService coll = new CollegueService();
        assertThrows(CollegueInvalideException.class,
                () -> coll.ajouterUnCollegue(
                        new Collegue(
                                "nom",
                                "prenom",
                                "email@",
                                LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).plusDays(1),
                                "http")));
    }
    @Test
    public void ajouter_un_collegue_test_age_valide(){
        //Test age valide
        CollegueService coll = new CollegueService();
        Collegue collegue = coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).minusDays(1),
                        "http"));
        assertEquals(LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).minusDays(1),collegue.getDateDeNaissance());
    }


    @Test
    public void modifier_email_matricule_invalide() {
        //matricule invalide
        CollegueService coll = new CollegueService();
        coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).minusDays(1),
                        "http"));
        assertThrows(CollegueNonTrouveException.class,
                () -> coll.modifierEmail("0bf06670-33e7-40df-b5ed-c6014b74b1f5","email@"));
    }
    @Test
    public void modifier_email_matricule_valide_email_invalide() {
        //email invalide
        CollegueService coll = new CollegueService();
        Collegue collegue=coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).minusDays(1),
                        "http"));
        assertThrows(CollegueInvalideException.class,
                () -> coll.modifierEmail(collegue.getMatricule(),"email"));
    }

    @Test
    public void modifier_email_matricule_valide_email_valide() {
        //email valide
        CollegueService coll = new CollegueService();
        Collegue collegue=coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).minusDays(1),
                        "http"));
        Collegue collegueModifier = coll.modifierEmail(collegue.getMatricule(),"robin@");
        assertEquals("robin@",collegueModifier.getEmail());
    }

    @Test
    public void modifier_photo_matricule_invalide() {
        //matricule invalide
        CollegueService coll = new CollegueService();
        coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).minusDays(1),
                        "http"));
        assertThrows(CollegueNonTrouveException.class,
                () -> coll.modifierPhotoUrl("0bf06670-33e7-40df-b5ed-c6014b74b1f5","http"));
    }
    @Test
    public void modifier_photo_matricule_valide_email_invalide() {
        //email invalide
        CollegueService coll = new CollegueService();
        Collegue collegue=coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).minusDays(1),
                        "http"));
        assertThrows(CollegueInvalideException.class,
                () -> coll.modifierPhotoUrl(collegue.getMatricule(),"htt"));
    }

    @Test
    public void modifier_photo_matricule_valide_email_valide() {
        //email valide
        CollegueService coll = new CollegueService();
        Collegue collegue=coll.ajouterUnCollegue(
                new Collegue(
                        "nom",
                        "prenom",
                        "email@",
                        LocalDate.now().minusYears(CollegueService.AGE_MINIMUM).minusDays(1),
                        "http"));
        Collegue collegueModifier = coll.modifierPhotoUrl(collegue.getMatricule(),"http://");
        assertEquals("http://",collegueModifier.getPhotoUrl());
    }



}