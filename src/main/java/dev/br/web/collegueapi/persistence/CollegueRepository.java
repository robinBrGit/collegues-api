package dev.br.web.collegueapi.persistence;

import dev.br.web.collegueapi.dto.CollegueLite;
import dev.br.web.collegueapi.entite.Collegue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CollegueRepository extends JpaRepository<Collegue,String> {

    public List<Collegue> findByNom(String nom);

    @Query("select new dev.br.web.collegueapi.dto.CollegueLite(c.matricule, c.nom,c.prenoms,c.email,c.dateDeNaissance,c.photoUrl) from Collegue c where c.matricule=:matricule")
    public Optional<CollegueLite> findByMatriculeLite(@Param("matricule") String matricule);

    public Optional<Collegue> findByMatricule(String matricule);

    public Optional<Collegue> findByEmail(String email);

    public boolean existsByEmail(String email);

}
