package dev.br.web.collegueapi.persistence;

import dev.br.web.collegueapi.entite.Collegue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Component
public class StartupDataInit {
    @Autowired
    CollegueRepository collegueRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {

        // TODO insérer des collègues en base de données
        // alimenter data avec des données fictives
        // Pour générer un matricule : `UUID.randomUUID().toString()`

        int count = 0;
        String[] noms = new String[]{"Martin","Bernard","Thomas","Petit","Robert","Richard","Durand","Dubois","Moreau","Laurent"};
        String[] prenoms = new String[]{"CAMILLE","LOUISE","LÉA","AMBRE","AGATHE","LOUIS","GABRIEL","LÉO","MAËL","PAUL"};

        while(count != 20){
            String matricule = UUID.randomUUID().toString();
            String nom = noms[(int)(Math.random()*(9))].toUpperCase();
            String prenom = prenoms[(int)(Math.random()*(9))];
            String email = prenom.toLowerCase()+nom.toLowerCase()+(1+(int)(Math.random()*(50-1)))+"@email.com";
            int annee = 1950+(int)(Math.random()*(50));
            int mois = 1+(int)(Math.random()*(12-1));
            int jour = 1+(int)(Math.random()*(28-1));
            collegueRepo.save(new Collegue(matricule,nom,prenom,email,
                    LocalDate.of(annee,mois,jour),
                    "https://www.electricien-meaux-dubot-hager.fr/media/original/13266/profil-neutre.png",
                    passwordEncoder.encode(prenom+nom),
                    Arrays.asList("ROLE_USER")));
            count++;
        }
        collegueRepo.save(new Collegue(UUID.randomUUID().toString(),"BROQUERIE","Robin",
                "robin.broquerie@gmail.com",LocalDate.of(1993,12,11),
                "https://www.electricien-meaux-dubot-hager.fr/media/original/13266/profil-neutre.png",
                passwordEncoder.encode("robinBr"),
                Arrays.asList("ROLE_ADMIN","ROLE_USER")));
    }
}
