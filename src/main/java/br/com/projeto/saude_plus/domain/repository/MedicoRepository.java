package br.com.projeto.saude_plus.domain.repository;

import br.com.projeto.saude_plus.domain.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    Optional<Medico> findByCrm(String crm);

    List<Medico> findByEspecialidadeNome(String nomeEspecialidade);

    List<Medico> findByNomeContainingIgnoreCase(String nome);

    List<Medico> findByAtivoTrue();

    List<Medico> findByAtivoFalse();
}
