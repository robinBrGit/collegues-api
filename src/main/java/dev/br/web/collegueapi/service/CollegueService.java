package dev.br.web.collegueapi.service;

import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueInvalideException;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;

import java.time.LocalDate;
import java.util.*;

public class CollegueService {
    private Map<String, Collegue> data = new HashMap<>();
    private final int AGE_MINIMUM = 18;
    private final int TAILLE_MIN_NOM = 2;
    private final int TAILLE_MIN_PRENOM = 2;
    private final int TAILLE_MIN_EMAIL = 3;

    public CollegueService() {
        // alimenter data avec des données fictives
        // Pour générer un matricule : `UUID.randomUUID().toString()`

        int count = 0;
        String[] noms = new String[]{"Martin","Bernard","Thomas","Petit","Robert","Richard","Durand","Dubois","Moreau","Laurent"};
        String[] prenoms = new String[]{"CAMILLE","LOUISE","LÉA","AMBRE","AGATHE","LOUIS","GABRIEL","LÉO","MAËL","PAUL"};

        while(count != 20){
            String matricule =UUID.randomUUID().toString();
            String nom = noms[(int)(Math.random()*(9-0))].toLowerCase();
            String prenom = prenoms[(int)(Math.random()*(9-0))];
            String email = prenom.toLowerCase()+nom.toLowerCase()+"@email.com";
            int annee = 1950+(int)(Math.random()*(50));
            int mois = 1+(int)(Math.random()*(12-1));
            int jour = 1+(int)(Math.random()*(28-1));
            data.put(matricule,new Collegue(matricule,nom,prenom,email, LocalDate.of(annee,mois,jour),"photo.PNG"));
            count++;
        }
    }

    public List<Collegue> rechercherParNom(String nomRecherche){
        List<Collegue> collegues = new ArrayList<>();
        for(Map.Entry<String,Collegue> c : data.entrySet()){
            if(c.getValue().getNom().contains(nomRecherche))collegues.add(c.getValue());
        }
        return collegues;
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
        if(!data.containsKey(matriculeRecherche))throw new CollegueNonTrouveException("Collegue non trouvé");
        return data.get(matriculeRecherche);
    }

    public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
        //  Vérifier que le nom et les prenoms ont chacun au moins 2 caractères
        //  Vérifier que l'email a au moins 3 caractères et contient `@`
        //  Vérifier que la photoUrl commence bien par `http`
        //  Vérifier que la date de naissance correspond à un age >= 18
        //  Si une des règles ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`.
        if(collegueAAjouter.getNom().length() < TAILLE_MIN_NOM
                || collegueAAjouter.getPrenoms().length()< TAILLE_MIN_PRENOM)throw new CollegueInvalideException("Le nom/prenom doit contenir 2 caractère minimum");
        if(collegueAAjouter.getEmail().length() < TAILLE_MIN_EMAIL
                || !collegueAAjouter.getEmail().contains("@"))throw new CollegueInvalideException("email invalide");
        if(!collegueAAjouter.getPhotoUrl().startsWith("http"))throw new CollegueInvalideException("url de la photo invalide");
        if(collegueAAjouter.getDateDeNaissance().plusYears(AGE_MINIMUM).isAfter(LocalDate.now()))throw new CollegueInvalideException("Age minimum = 18 ans");

        //  générer un matricule pour ce collègue (`UUID.randomUUID().toString()`)
        String matricule = UUID.randomUUID().toString();
        collegueAAjouter.setMatricule(matricule);

        //  Sauvegarder le collègue
        data.put(matricule,collegueAAjouter);
        return collegueAAjouter;
    }

    public Collegue modifierEmail(String matricule, String email) throws CollegueNonTrouveException, CollegueInvalideException {

        //  retourner une exception `CollegueNonTrouveException`
        //  si le matricule ne correspond à aucun collègue
        if(!data.containsKey(matricule))throw new CollegueNonTrouveException("Matricule non existant");

        //  Vérifier que l'email a au moins 3 caractères et contient `@`
        //  Si la règle ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`. avec un message approprié.
        if(email.length() < 3 || !email.contains("@"))throw new CollegueInvalideException("Email invalide");

        // Modifier le collègue
        Collegue collegue = data.get(matricule);
        collegue.setEmail(email);
        data.put(matricule,collegue);
        return collegue;
    }

    public Collegue modifierPhotoUrl(String matricule, String photoUrl) throws CollegueNonTrouveException, CollegueInvalideException {

        //  si le matricule ne correspond à aucun collègue
        if(!data.containsKey(matricule))throw new CollegueNonTrouveException("Matricule non existant");

        //  Vérifier que la photoUrl commence bien par `http`
        //  Si la règle ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`. avec un message approprié.
        if(!photoUrl.startsWith("http"))throw new CollegueInvalideException("photoUrl invalide");

        //  Modifier le collègue
        Collegue collegue = data.get(matricule);
        collegue.setPhotoUrl(photoUrl);
        data.put(matricule,collegue);
        return collegue;
    }
}
