package dev.br.web.collegueapi.service;

import dev.br.web.collegueapi.dto.CollegueLite;
import dev.br.web.collegueapi.dto.PhotoDTO;
import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueInvalideException;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;
import dev.br.web.collegueapi.persistence.CollegueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public List<Collegue> rechercherParNom(String nomRecherche) {
        return collegueRepository.findByNom(nomRecherche.toUpperCase());
    }

    public CollegueLite rechercherParMatriculeLite(String matriculeRecherche) throws CollegueNonTrouveException {
        Optional<CollegueLite> collegueOpt = collegueRepository.findByMatriculeLite(matriculeRecherche);
        return collegueOpt.orElseThrow(() -> new CollegueNonTrouveException("Collegue non trouvé"));
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
        Optional<Collegue> collegueOpt = collegueRepository.findByMatricule(matriculeRecherche);
        return collegueOpt.orElseThrow(() -> new CollegueNonTrouveException("Collegue non trouvé"));
    }

    public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {
        //  Vérifier que le nom et les prenoms ont chacun au moins 2 caractères
        //  Vérifier que l'email a au moins 3 caractères et contient `@`
        //  Vérifier que la photoUrl commence bien par `http`
        //  Vérifier que la date de naissance correspond à un age >= 18
        //  Si une des règles ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`.
        Map<String, String> erreurs = new HashMap<>();
        if (collegueAAjouter.getNom().length() < TAILLE_MIN_NOM)
            erreurs.put("nom", "Le nom doit contenir " + TAILLE_MIN_PRENOM + " caractère minimum");

        if (collegueAAjouter.getPrenoms().length() < TAILLE_MIN_PRENOM)
            erreurs.put("prenoms", "Le prenom doit contenir " + TAILLE_MIN_PRENOM + " caractère minimum");

        if (collegueAAjouter.getEmail().length() < TAILLE_MIN_EMAIL
                || !collegueAAjouter.getEmail().contains("@"))
            erreurs.put("email", "email invalide");

        if (collegueAAjouter.getDateDeNaissance().plusYears(AGE_MINIMUM).isAfter(LocalDate.now()))
            erreurs.put("dateNaissance", "Age minimum = " + AGE_MINIMUM + " ans");

        if (!collegueAAjouter.getPhotoUrl().startsWith("http"))
            erreurs.put("photoUrl", "url de la photo invalide");

        if(!erreurs.isEmpty())throw new CollegueInvalideException(erreurs);


        //  générer un matricule pour ce collègue (`UUID.randomUUID().toString()`)
        String matricule = UUID.randomUUID().toString();
        collegueAAjouter.setMatricule(matricule);
        collegueAAjouter.setNom(collegueAAjouter.getNom().toUpperCase());

        //  Sauvegarder le collègue
        collegueRepository.save(collegueAAjouter);
        return collegueAAjouter;
    }

    public Collegue modifierEmail(String matricule, String email) throws CollegueNonTrouveException, CollegueInvalideException {

        //  retourner une exception `CollegueNonTrouveException`
        //  si le matricule ne correspond à aucun collègue
        Optional<Collegue> collegueOpt = collegueRepository.findByMatricule(matricule);
        Collegue collegue = collegueOpt.orElseThrow(() -> new CollegueNonTrouveException("Collegue non trouvé"));

        //  Vérifier que l'email a au moins 3 caractères et contient `@`
        //  Si la règle ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`. avec un message approprié.
        if (email.length() < 3 || !email.contains("@")) throw new CollegueInvalideException("Email invalide");

        // Modifier le collègue
        collegue.setEmail(email);
        collegueRepository.save(collegue);
        return collegue;
    }

    public Collegue modifierPhotoUrl(String matricule, String photoUrl) throws CollegueNonTrouveException, CollegueInvalideException {

        //  si le matricule ne correspond à aucun collègue
        Optional<Collegue> collegueOpt = collegueRepository.findByMatricule(matricule);
        Collegue collegue = collegueOpt.orElseThrow(() -> new CollegueNonTrouveException("Collegue non trouvé"));

        //  Vérifier que la photoUrl commence bien par `http`
        //  Si la règle ci-dessus n'est pas valide, générer une exception :
        // `CollegueInvalideException`. avec un message approprié.
        if (!photoUrl.startsWith("http")) throw new CollegueInvalideException("photoUrl invalide");

        //  Modifier le collègue
        collegue.setPhotoUrl(photoUrl);
        collegueRepository.save(collegue);
        return collegue;
    }

    public Boolean isEmailExist(String email){
        return collegueRepository.existsByEmail(email);
    }

    public List<PhotoDTO> getAllCollegueForGallerie(){
        List<Collegue> lesCollegues = collegueRepository.findAll();
        List<PhotoDTO> photoDTOList = new ArrayList<>();
        for (Collegue collegue : lesCollegues){
            photoDTOList.add(new PhotoDTO(collegue.getMatricule(),collegue.getPhotoUrl()));
        }
        return photoDTOList;
    }
}
