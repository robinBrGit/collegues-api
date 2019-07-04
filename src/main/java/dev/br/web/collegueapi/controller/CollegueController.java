package dev.br.web.collegueapi.controller;

import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueInvalideException;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;
import dev.br.web.collegueapi.service.CollegueService;
import dev.br.web.collegueapi.util.Constantes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CollegueController {
    private CollegueService lesCollegues = Constantes.COLLEGUE_SERVICE;

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
    @RequestMapping(method = RequestMethod.GET,
    path = "/collegues/{matricule}")
    public Collegue getCollegueByMatricule(@PathVariable String matricule) throws CollegueNonTrouveException {
        return lesCollegues.rechercherParMatricule(matricule);
    }

    @RequestMapping(method = RequestMethod.POST,
    path = "/collegues")
    public ResponseEntity<Object> ajouterCollegues(@RequestBody Collegue collegue) throws CollegueInvalideException {
        Collegue col = lesCollegues.ajouterUnCollegue(collegue);
        return ResponseEntity.status(HttpStatus.CREATED).body(col.getMatricule());
    }
}
