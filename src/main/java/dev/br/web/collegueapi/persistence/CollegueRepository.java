package dev.br.web.collegueapi.persistence;

import dev.br.web.collegueapi.entite.Collegue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollegueRepository extends JpaRepository<Collegue,String> {

    public List<Collegue> findByNom(String nom);

    public Optional<Collegue> findByMatricule(String matricule);

}
