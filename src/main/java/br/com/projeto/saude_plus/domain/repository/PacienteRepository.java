package br.com.projeto.saude_plus.domain.repository;

import br.com.projeto.saude_plus.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByEmail(String email);

    Optional<Paciente> findByCpf(String cpf);

    List<Paciente> findByAtivoTrue();

    List<Paciente> findByAtivoFalse();

    List<Paciente> findByNomeContainingIgnoreCase(String nome);

}
