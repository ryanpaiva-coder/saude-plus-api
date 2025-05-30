package br.com.projeto.saude_plus.domain.repository;

import br.com.projeto.saude_plus.domain.model.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, String> {
    
}