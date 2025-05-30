package br.com.projeto.saude_plus.domain.repository;

import br.com.projeto.saude_plus.domain.enums.StatusConsulta;
import br.com.projeto.saude_plus.domain.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByMedicoId(Long idMedico);

    List<Consulta> findByPacienteId(Long idPaciente);

    List<Consulta> findByStatus(StatusConsulta status);

    List<Consulta> findByMedicoIdAndInicioAfter(Long idMedico, LocalDateTime dataHora);

    boolean existsByMedicoIdAndInicio(Long idMedico, LocalDateTime inicio);

    List<Consulta> findByMedicoIdAndInicioBetween(Long idMedico, LocalDateTime inicio, LocalDateTime fim);

    List<Consulta> findByPacienteIdAndInicioBetween(Long idPaciente, LocalDateTime inicio, LocalDateTime fim);

    List<Consulta> findByPacienteIdAndInicioAfter(Long idPaciente, LocalDateTime dataHora);

    List<Consulta> findByMedicoIdAndStatusAndInicioBetween(
        Long idMedico, StatusConsulta status, LocalDateTime inicio, LocalDateTime fim
    );
    
}