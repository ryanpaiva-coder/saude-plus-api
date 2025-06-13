package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Paciente;
import br.com.projeto.saude_plus.domain.model.Role;
import br.com.projeto.saude_plus.domain.repository.PacienteRepository;
import br.com.projeto.saude_plus.domain.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Paciente cadastrarPaciente(Paciente paciente) {
        validarPacienteExistente(paciente);

        paciente.setAtivo(true);
        paciente.setSenha(passwordEncoder.encode(paciente.getSenha()));
        paciente.setRole(buscarRolePaciente());

        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        emailService.enviarEmailBoasVindas(pacienteSalvo);
        return pacienteSalvo;
    }

    private void validarPacienteExistente(Paciente paciente) {
        if (pacienteRepository.findByEmail(paciente.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um paciente com este e-mail.");
        }
        if (pacienteRepository.findByCpf(paciente.getCpf()).isPresent()) {
            throw new RuntimeException("Já existe um paciente com este CPF.");
        }
    }

    private Role buscarRolePaciente() {
        return roleRepository.findByNome("PACIENTE")
                .orElseThrow(() -> new RuntimeException("Role PACIENTE não encontrada"));
    }

    @Transactional
    public Paciente atualizarPaciente(Long id, Paciente dadosAtualizados) {
        Paciente paciente = buscarPorId(id);
        atualizarDadosPaciente(paciente, dadosAtualizados);
        return pacienteRepository.save(paciente);
    }

    @Transactional
    private void atualizarDadosPaciente(Paciente paciente, Paciente dadosAtualizados) {
        paciente.setNome(dadosAtualizados.getNome());
        paciente.setCpf(dadosAtualizados.getCpf());
        paciente.setEmail(dadosAtualizados.getEmail());
        paciente.setSenha(dadosAtualizados.getSenha());
        paciente.setSexo(dadosAtualizados.getSexo());
        paciente.setTelefone(dadosAtualizados.getTelefone());
        paciente.setDataNascimento(dadosAtualizados.getDataNascimento());
        paciente.setDescricao(dadosAtualizados.getDescricao());
        paciente.setEndereco(dadosAtualizados.getEndereco());
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Optional<Paciente> buscarPorEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }

    public Optional<Paciente> buscarPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public List<Paciente> listarAtivos() {
        return pacienteRepository.findByAtivoTrue();
    }

    public List<Paciente> listarDesativados() {
        return pacienteRepository.findByAtivoFalse();
    }

    public List<Paciente> buscarPorNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public void desativarPaciente(Long id) {
        Paciente paciente = buscarPorId(id);
        paciente.setAtivo(false);
        pacienteRepository.save(paciente);
        emailService.enviarEmailDesativamento(paciente);
    }
}
