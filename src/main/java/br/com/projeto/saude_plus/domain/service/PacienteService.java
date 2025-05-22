package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Paciente;
import br.com.projeto.saude_plus.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrarPaciente(Paciente paciente) {
        paciente.setAtivo(true);
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizarPaciente(Long id, Paciente dadosAtualizados) {
        Paciente paciente = buscarPorId(id);
        paciente.setNome(dadosAtualizados.getNome());
        paciente.setCpf(dadosAtualizados.getCpf());
        paciente.setEmail(dadosAtualizados.getEmail());
        paciente.setSenha(dadosAtualizados.getSenha());
        paciente.setSexo(dadosAtualizados.getSexo());
        paciente.setTelefone(dadosAtualizados.getTelefone());
        paciente.setDataNascimento(dadosAtualizados.getDataNascimento());
        paciente.setDescricao(dadosAtualizados.getDescricao());
        paciente.setEndereco(dadosAtualizados.getEndereco());
        return pacienteRepository.save(paciente);
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
    }
}
