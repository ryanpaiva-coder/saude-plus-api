package br.com.projeto.saude_plus.domain.repository;

import br.com.projeto.saude_plus.domain.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    Optional<Especialidade> findByNome(String nome);
    
}