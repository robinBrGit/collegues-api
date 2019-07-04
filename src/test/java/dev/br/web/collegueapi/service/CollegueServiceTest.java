package dev.br.web.collegueapi.service;

import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueInvalideException;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CollegueServiceTest {

    // TODO: 04/07/2019 Optimiser les test

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
        testAge.setDateDeNaissance(LocalDate.now().minusYears(18).minusDays(1));
        //test age valide
        try {
            coll.ajouterUnCollegue(testAge);
            assertTrue(true);
        }catch (CollegueInvalideException e){
            assertTrue(false);
        }
    }


    @Test
    public void modifierEmail() {
        CollegueService coll = new CollegueService();
        Collegue test = new Collegue();
        test.setNom("nom");
        test.setPrenoms("prenom");
        test.setEmail("zaeaze@");
        test.setDateDeNaissance(LocalDate.now().minusYears(18).minusDays(1));
        test.setPhotoUrl("http");
        try {
            test=coll.ajouterUnCollegue(test);
        }catch (CollegueInvalideException e){
            assertTrue(false);
        }
        //On test de modifier un matricule inexistant
        String matricule = "0bf06670-33e7-40df-b5ed-c6014b74b1f5";
        String email = "ooo@o";
        try {
            coll.modifierEmail(matricule,email);
            assertTrue(false);
        }
        catch (CollegueNonTrouveException e){
            assertTrue(true);
        }
        catch (CollegueInvalideException e){
            assertTrue(false);
        }
        //on test de modfifier un matricule existant avec un email invalide
        matricule  = test.getMatricule();
        email = "er";
        try {
            coll.modifierEmail(matricule,email);
            assertTrue(false);
        }
        catch (CollegueNonTrouveException e){
            assertTrue(false);
        }
        catch (CollegueInvalideException e){
            assertTrue(true);
        }
        email = "zefzefzfe";
        try {
            coll.modifierEmail(matricule,email);
            assertTrue(false);
        }
        catch (CollegueNonTrouveException e){
            assertTrue(false);
        }
        catch (CollegueInvalideException e){
            assertTrue(true);
        }
        //on test une email valide
        email="ezae@";
        try {
            coll.modifierEmail(matricule,email);
            assertTrue(true);
        }
        catch (CollegueNonTrouveException e){
            assertTrue(false);
        }
        catch (CollegueInvalideException e){
            assertTrue(false);
        }

    }

    @Test
    public void modifierPhotoUrl() {
        CollegueService coll = new CollegueService();
        Collegue test = new Collegue();
        test.setNom("nom");
        test.setPrenoms("prenom");
        test.setEmail("zaeaze@");
        test.setDateDeNaissance(LocalDate.now().minusYears(18).minusDays(1));
        test.setPhotoUrl("http");
        try {
            test=coll.ajouterUnCollegue(test);
        }catch (CollegueInvalideException e){
            assertTrue(false);
        }
        //On test de modifier une photo invalide
        String matricule = "0bf06670-33e7-40df-b5ed-c6014b74b1f5";
        String photoUrl = "ooo";
        try {
            coll.modifierPhotoUrl(matricule,photoUrl);
            assertTrue(false);
        }
        catch (CollegueNonTrouveException e){
            assertTrue(true);
        }
        catch (CollegueInvalideException e){
            assertTrue(false);
        }
        //on test de modfifier un matricule existant avec une photo invalide
        matricule  = test.getMatricule();
        photoUrl = "ere";
        try {
            coll.modifierPhotoUrl(matricule,photoUrl);
            assertTrue(false);
        }
        catch (CollegueNonTrouveException e){
            assertTrue(false);
        }
        catch (CollegueInvalideException e){
            assertTrue(true);
        }
        //on test une photo valide
        photoUrl="http";
        try {
            coll.modifierPhotoUrl(matricule,photoUrl);
            assertTrue(true);
        }
        catch (CollegueNonTrouveException e){
            assertTrue(false);
        }
        catch (CollegueInvalideException e){
            assertTrue(false);
        }
    }
}