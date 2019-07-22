package dev.br.web.collegueapi.controller;

import dev.br.web.collegueapi.dto.CollegueLite;
import dev.br.web.collegueapi.dto.IdentiteCollegue;
import dev.br.web.collegueapi.dto.PhotoDTO;
import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.service.CollegueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CollegueController {
    @Autowired
    private CollegueService lesCollegues;

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET,
    path = "/collegues")
    public List<String> getColleguesMatricule(@RequestParam String nom){
        List<Collegue> collegues = lesCollegues.rechercherParNom(nom);
        List<String> lesMatriculesCollegue = new ArrayList<>();
        for(Collegue c : collegues) {
            lesMatriculesCollegue.add(c.getMatricule());
        }
        return lesMatriculesCollegue;
    }
    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET,
    path = "/collegues/{matricule}")
    public CollegueLite getCollegueByMatricule(@PathVariable String matricule){
        return lesCollegues.rechercherParMatriculeLite(matricule);
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST,
    path = "/collegues")
    public ResponseEntity<Object> ajouterCollegue(@RequestBody Collegue collegue){
        if (collegue.isValid()){
            Collegue col = lesCollegues.ajouterUnCollegue(collegue);
            return ResponseEntity.status(HttpStatus.CREATED).body(col.getMatricule());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST,
    path = "/email")
    public ResponseEntity<Object> isEmailExist(@RequestBody String email){
        return ResponseEntity.status(HttpStatus.OK).body(lesCollegues.isEmailExist(email));
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.PATCH,
    path = "/collegues/{matricule}")
    public ResponseEntity<Object> modifierCollegue(@PathVariable String matricule,@RequestBody Collegue collegue){
        boolean update = false;
        String body = "";
        if(collegue.getEmail() != null && !collegue.getEmail().equals("")){
            lesCollegues.modifierEmail(matricule,collegue.getEmail());
            update = true;
            body +="Email modifié<br/>";
        }
        if(collegue.getPhotoUrl() != null && !collegue.getPhotoUrl().equals("")){
            lesCollegues.modifierPhotoUrl(matricule,collegue.getPhotoUrl());
            update = true;
            body +="Photo modifié<br/>";
        }
        return (update)? ResponseEntity.status(HttpStatus.CREATED).body(body) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametre invalide");
    }

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET,
    path = "/collegues/photos")
    public List<PhotoDTO> getColleguesPhotos(){
        return lesCollegues.getAllCollegueForGallerie();
    }

    @Secured("ROLE_USER")
    @GetMapping(path = "/me")
    public IdentiteCollegue getIdentiteCollegue(){
        String matricule = SecurityContextHolder.getContext().getAuthentication().getName();
        Collegue collegue = lesCollegues.rechercherParMatricule(matricule);
        return new IdentiteCollegue(collegue.getMatricule(),collegue.getNom(),collegue.getPrenoms(),collegue.getRoles());
    }
}
