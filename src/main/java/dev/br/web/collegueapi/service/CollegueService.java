package dev.br.web.collegueapi.service;

import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;

import java.time.LocalDate;
import java.util.*;

public class CollegueService {
    private Map<String, Collegue> data = new HashMap<>();

    public CollegueService() {
        // TODO alimenter data avec des données fictives
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
}
