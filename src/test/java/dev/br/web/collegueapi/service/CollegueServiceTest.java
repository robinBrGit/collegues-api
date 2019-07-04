package dev.br.web.collegueapi.service;

import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueInvalideException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CollegueServiceTest {


    @Test
    public void ajouterUnCollegueTestNomPrenom() {
        //Test Nom et Prenom
        CollegueService coll = new CollegueService();
        Collegue testNomPrenom = new Collegue();
        testNomPrenom.setNom("e");
        testNomPrenom.setPrenoms("robin");
        testNomPrenom.setEmail("zaeaze@");
        testNomPrenom.setDateDeNaissance(LocalDate.of(1993,12,11));
        testNomPrenom.setPhotoUrl("http");

        //Test avec un nom invalide et un prenom valide
        try {
            coll.ajouterUnCollegue(testNomPrenom);
        }catch (CollegueInvalideException e){
            assertTrue(true);
        }
        testNomPrenom.setNom("richard");
        testNomPrenom.setPrenoms("r");
        //Test avec un prenom invalide et un nom valide
        try{
            coll.ajouterUnCollegue(testNomPrenom);
        }
        catch (CollegueInvalideException e){
            assertTrue(true);
        }
        testNomPrenom.setNom("ert");
        testNomPrenom.setPrenoms("eae");
        //Test avec un nom et prenom valide
        try{
            coll.ajouterUnCollegue(testNomPrenom);
            assertTrue(true);
        }
        catch (CollegueInvalideException e){
            assertTrue(false);
        }
    }

    @Test
    public void ajouterUnCollegueTestEmail(){
        //Test email
        CollegueService coll = new CollegueService();
        Collegue testEmail = new Collegue();
        testEmail.setNom("nom");
        testEmail.setPrenoms("prenom");
        testEmail.setEmail("zaeaze@");
        testEmail.setDateDeNaissance(LocalDate.of(1993,12,11));
        testEmail.setPhotoUrl("http");
        //Test email valide
        try{
            coll.ajouterUnCollegue(testEmail);
            assertTrue(true);
        }catch (CollegueInvalideException e){
            assertTrue(false);
        }
        testEmail.setEmail("ererzrze");
        //test email sans @
        try {
            coll.ajouterUnCollegue(testEmail);
            assertTrue(false);
        }catch (CollegueInvalideException e){
            assertTrue(true);
        }
        testEmail.setEmail("e@");
        //Test email avec moins de 3 caractère
        try {
            coll.ajouterUnCollegue(testEmail);
            assertTrue(false);
        }catch (CollegueInvalideException e){
            assertTrue(true);
        }
    }

    @Test
    public void ajouterUnCollegueTestPhotoUrl(){
        //Test photoUrl
        CollegueService coll = new CollegueService();
        Collegue testPhotoUrl = new Collegue();
        testPhotoUrl.setNom("nom");
        testPhotoUrl.setPrenoms("prenom");
        testPhotoUrl.setEmail("zaeaze@");
        testPhotoUrl.setDateDeNaissance(LocalDate.of(1993,12,11));
        testPhotoUrl.setPhotoUrl("www.");

        //test photoUrl invalide
        try {
            coll.ajouterUnCollegue(testPhotoUrl);
            assertTrue(false);
        }catch (CollegueInvalideException e){
            assertTrue(true);
        }
        testPhotoUrl.setPhotoUrl("http");
        //test photoUrl valide
        try {
            coll.ajouterUnCollegue(testPhotoUrl);
            assertTrue(true);
        }catch (CollegueInvalideException e){
            assertTrue(false);
        }
    }

    @Test
    public void ajouterUnCollegueTestAge(){
        //Test Age
        CollegueService coll = new CollegueService();
        Collegue testAge = new Collegue();
        testAge.setNom("nom");
        testAge.setPrenoms("prenom");
        testAge.setEmail("zaeaze@");
        testAge.setDateDeNaissance(LocalDate.now().minusYears(18).plusDays(1));
        testAge.setPhotoUrl("http");

        //test age invalide
        try {
            coll.ajouterUnCollegue(testAge);
            assertTrue(false);

        }catch (CollegueInvalideException e){
            assertTrue(true);
        }
        testAge.setDateDeNaissance(LocalDate.of(1993,12,11));
        //test age valide
        try {
            coll.ajouterUnCollegue(testAge);
            assertTrue(true);
        }catch (CollegueInvalideException e){
            assertTrue(false);
        }
    }



}