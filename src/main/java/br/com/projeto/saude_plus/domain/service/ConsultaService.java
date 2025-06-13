package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.enums.StatusConsulta;
import br.com.projeto.saude_plus.domain.model.Consulta;
import br.com.projeto.saude_plus.domain.model.Medico;
import br.com.projeto.saude_plus.domain.model.Paciente;
import br.com.projeto.saude_plus.domain.repository.ConsultaRepository;
import br.com.projeto.saude_plus.domain.repository.MedicoRepository;
import br.com.projeto.saude_plus.domain.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public Consulta agendarConsulta(Consulta consulta) {
        validarAgendamento(consulta);

        Medico medico = buscarMedicoPorId(consulta.getMedico().getId());
        Paciente paciente = buscarPacientePorId(consulta.getPaciente().getId());

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setStatus(StatusConsulta.AGENDADA);

        Consulta consultaSalva = consultaRepository.save(consulta);

        notificarAgendamento(consultaSalva);

        return consultaSalva;
    }

    @Transactional
    public Consulta desmarcarConsulta(Long id) {
        Consulta consulta = buscarConsultaPorId(id);
        consulta.setStatus(StatusConsulta.DESMARCADA);

        Consulta consultaAtualizada = consultaRepository.save(consulta);

        notificarCancelamento(consultaAtualizada);

        return consultaAtualizada;
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Consulta buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada para o id: " + id));
    }

    public List<Consulta> listarPorMedico(Long idMedico) {
        return consultaRepository.findByMedicoId(idMedico);
    }

    public List<Consulta> listarPorPaciente(Long idPaciente) {
        return consultaRepository.findByPacienteId(idPaciente);
    }

    public List<Consulta> listarPorStatus(StatusConsulta status) {
        return consultaRepository.findByStatus(status);
    }

    public List<Consulta> listarConsultasFuturasMedico(Long idMedico) {
        return consultaRepository.findByMedicoIdAndInicioAfter(idMedico, LocalDateTime.now());
    }

    public List<Consulta> listarConsultasFuturasPaciente(Long idPaciente) {
        return consultaRepository.findByPacienteIdAndInicioAfter(idPaciente, LocalDateTime.now());
    }

    private void validarAgendamento(Consulta consulta) {
        validarHorarioAntecedencia(consulta.getInicio());
        validarHorarioFuncionamento(consulta.getInicio());
        validarConflitoConsulta(consulta);
    }

    private Medico buscarMedicoPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado para o id: " + id));
    }

    private Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado para o id: " + id));
    }

    private void notificarAgendamento(Consulta consulta) {
        emailService.enviarEmailConsultaAgendadaPaciente(
                consulta.getPaciente().getEmail(),
                consulta.getPaciente().getNome(),
                consulta.getInicio().toLocalDate().toString(),
                consulta.getInicio().toLocalTime().toString(),
                consulta.getMedico().getNome(),
                consulta.getMedico().getEspecialidade().getNome()
        );

        emailService.enviarEmailConsultaAgendadaMedico(
                consulta.getMedico().getEmail(),
                consulta.getMedico().getNome(),
                consulta.getInicio().toLocalDate().toString(),
                consulta.getInicio().toLocalTime().toString(),
                consulta.getPaciente().getNome(),
                consulta.getPaciente().getTelefone()
        );
    }

    private void notificarCancelamento(Consulta consulta) {
        emailService.enviarEmailConsultaCanceladaPaciente(
                consulta.getPaciente().getEmail(),
                consulta.getPaciente().getNome(),
                consulta.getInicio().toLocalDate().toString(),
                consulta.getInicio().toLocalTime().toString(),
                consulta.getMedico().getNome(),
                consulta.getMedico().getEspecialidade().getNome()
        );

        emailService.enviarEmailConsultaCanceladaMedico(
                consulta.getMedico().getEmail(),
                consulta.getMedico().getNome(),
                consulta.getInicio().toLocalDate().toString(),
                consulta.getInicio().toLocalTime().toString(),
                consulta.getPaciente().getNome(),
                consulta.getPaciente().getTelefone()
        );
    }

    private void validarHorarioAntecedencia(LocalDateTime inicio) {
        if (inicio.isBefore(LocalDateTime.now().plusHours(1))) {
            throw new RuntimeException("A consulta deve ser marcada com pelo menos 1 hora de antecedência.");
        }
    }

    private void validarHorarioFuncionamento(LocalDateTime inicio) {
        int hora = inicio.getHour();
        boolean horarioValido = (hora >= 7 && hora <= 11) || (hora >= 14 && hora <= 17);
        if (!horarioValido) {
            throw new RuntimeException("A consulta só pode ser marcada entre 07:00-11:00 ou 14:00-17:00.");
        }
    }

    private void validarConflitoConsulta(Consulta consulta) {
        LocalDateTime inicio = consulta.getInicio();
        LocalDateTime inicioJanela = inicio.minusMinutes(59);
        LocalDateTime fimJanela = inicio.plusMinutes(59);

        List<Consulta> conflitos = consultaRepository.findByMedicoIdAndInicioBetween(
                consulta.getMedico().getId(), inicioJanela, fimJanela);

        boolean existeConflito = conflitos.stream()
                .anyMatch(c -> !c.getId().equals(consulta.getId()) && c.getStatus() == StatusConsulta.AGENDADA);

        if (existeConflito) {
            throw new RuntimeException("Já existe uma consulta marcada para este médico em um intervalo de 1 hora.");
        }
    }
}