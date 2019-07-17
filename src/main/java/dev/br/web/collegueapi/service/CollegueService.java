package dev.br.web.collegueapi.service;

import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueInvalideException;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;
import dev.br.web.collegueapi.persistence.CollegueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CollegueService {
    @Autowired
    private CollegueRepository collegueRepository;

    public static final int AGE_MINIMUM = 18;
    public static final int TAILLE_MIN_NOM = 2;
    public static final int TAILLE_MIN_PRENOM = 2;
    public static final int TAILLE_MIN_EMAIL = 3;

    public CollegueService() {

    }

    public List<Collegue> rechercherParNom(String nomRecherche){
        return collegueRepository.findByNom(nomRecherche);
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
        Optional<Collegue> collegueOpt = collegueRepository.findByMatricule(matriculeRecherche);
        return collegueOpt.orElseThrow(()->new CollegueNonTrouveException("Collegue non trouvé"));
    }

    public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
        //  Vérifier que le nom et les prenoms ont chacun au moins 2 caractères
        //  Vérifier que l'email a au moins 3 caractères et contient `@`
        //  Vérifier que la photoUrl commence bien par `http`
        //  Vérifier que la date de naissance correspond à un age >= 18
        //  Si une des règles ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`.
        if(collegueAAjouter.getNom().length() < TAILLE_MIN_NOM
                || collegueAAjouter.getPrenoms().length()< TAILLE_MIN_PRENOM)throw new CollegueInvalideException("Le nom/prenom doit contenir "+TAILLE_MIN_PRENOM+" caractère minimum");
        if(collegueAAjouter.getEmail().length() < TAILLE_MIN_EMAIL
                || !collegueAAjouter.getEmail().contains("@"))throw new CollegueInvalideException("email invalide");
        if(collegueAAjouter.getDateDeNaissance().plusYears(AGE_MINIMUM).isAfter(LocalDate.now()))throw new CollegueInvalideException("Age minimum = "+AGE_MINIMUM+" ans");
        if(!collegueAAjouter.getPhotoUrl().startsWith("http"))throw new CollegueInvalideException("url de la photo invalide");


        //  générer un matricule pour ce collègue (`UUID.randomUUID().toString()`)
        String matricule = UUID.randomUUID().toString();
        collegueAAjouter.setMatricule(matricule);

        //  Sauvegarder le collègue
        collegueRepository.save(collegueAAjouter);
        return collegueAAjouter;
    }

    public Collegue modifierEmail(String matricule, String email) throws CollegueNonTrouveException, CollegueInvalideException {

        //  retourner une exception `CollegueNonTrouveException`
        //  si le matricule ne correspond à aucun collègue
        Optional<Collegue> collegueOpt = collegueRepository.findByMatricule(matricule);
        Collegue collegue = collegueOpt.orElseThrow(()->new CollegueNonTrouveException("Collegue non trouvé"));

        //  Vérifier que l'email a au moins 3 caractères et contient `@`
        //  Si la règle ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`. avec un message approprié.
        if(email.length() < 3 || !email.contains("@"))throw new CollegueInvalideException("Email invalide");

        // Modifier le collègue
        collegue.setEmail(email);
        collegueRepository.save(collegue);
        return collegue;
    }

    public Collegue modifierPhotoUrl(String matricule, String photoUrl) throws CollegueNonTrouveException, CollegueInvalideException {

        //  si le matricule ne correspond à aucun collègue
        Optional<Collegue> collegueOpt = collegueRepository.findByMatricule(matricule);
        Collegue collegue = collegueOpt.orElseThrow(()->new CollegueNonTrouveException("Collegue non trouvé"));

        //  Vérifier que la photoUrl commence bien par `http`
        //  Si la règle ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`. avec un message approprié.
        if(!photoUrl.startsWith("http"))throw new CollegueInvalideException("photoUrl invalide");

        //  Modifier le collègue
        collegue.setPhotoUrl(photoUrl);
        collegueRepository.save(collegue);
        return collegue;
    }
}
