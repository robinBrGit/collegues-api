package dev.br.web.collegueapi.controller;

import dev.br.web.collegueapi.entite.Collegue;
import dev.br.web.collegueapi.exception.CollegueNonTrouveException;
import dev.br.web.collegueapi.service.CollegueService;
import dev.br.web.collegueapi.util.Constantes;
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
}
