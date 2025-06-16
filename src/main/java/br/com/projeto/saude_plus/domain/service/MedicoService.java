package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Clinica;
import br.com.projeto.saude_plus.domain.model.Especialidade;
import br.com.projeto.saude_plus.domain.model.Medico;
import br.com.projeto.saude_plus.domain.model.Role;
import br.com.projeto.saude_plus.domain.repository.ClinicaRepository;
import br.com.projeto.saude_plus.domain.repository.EspecialidadeRepository;
import br.com.projeto.saude_plus.domain.repository.MedicoRepository;
import br.com.projeto.saude_plus.domain.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Medico cadastrarMedico(Medico medico, Long idEspecialidade) {
        medico.setAtivo(true);
        medico.setSenha(codificarSenha(medico.getSenha()));
        medico.setClinica(buscarPrimeiraClinica());
        medico.setEspecialidade(buscarEspecialidadePorId(idEspecialidade));
        medico.setRole(buscarRoleMedico());

        Medico medicoSalvo = medicoRepository.save(medico);
        // emailService.enviarEmailBoasVindas(medicoSalvo);
        return medicoSalvo;
    }

    @Transactional
    public Medico atualizarMedico(Long id, Medico dadosAtualizados) {
        Medico medico = buscarPorId(id);
        atualizarDadosMedico(medico, dadosAtualizados);
        return medicoRepository.save(medico);
    }

    @Transactional
    public void desativarMedico(Long id) {
        Medico medico = buscarPorId(id);
        medico.setAtivo(false);
        medicoRepository.save(medico);
        // emailService.enviarEmailDesativamento(medico);
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado para o id: " + id));
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public List<Medico> listarAtivos() {
        return medicoRepository.findByAtivoTrue();
    }

    public List<Medico> listarDesativados() {
        return medicoRepository.findByAtivoFalse();
    }

    public Optional<Medico> buscarPorCrm(String crm) {
        return medicoRepository.findByCrm(crm);
    }

    public List<Medico> buscarPorEspecialidade(String nomeEspecialidade) {
        return medicoRepository.findByEspecialidadeNome(nomeEspecialidade);
    }

    public List<Medico> buscarPorNome(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    private String codificarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    private Clinica buscarPrimeiraClinica() {
        return clinicaRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma clínica cadastrada"));
    }

    private Especialidade buscarEspecialidadePorId(Long idEspecialidade) {
        return especialidadeRepository.findById(idEspecialidade)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada para o id: " + idEspecialidade));
    }

    private Role buscarRoleMedico() {
        return roleRepository.findByNome("MEDICO")
                .orElseThrow(() -> new RuntimeException("Role MEDICO não encontrada"));
    }

    private void atualizarDadosMedico(Medico medico, Medico dadosAtualizados) {
        medico.setNome(dadosAtualizados.getNome());
        medico.setCrm(dadosAtualizados.getCrm());
        medico.setEspecialidade(dadosAtualizados.getEspecialidade());
        medico.setClinica(dadosAtualizados.getClinica());
        medico.setEmail(dadosAtualizados.getEmail());
        medico.setTelefone(dadosAtualizados.getTelefone());
        medico.setSexo(dadosAtualizados.getSexo());
        medico.setDataNascimento(dadosAtualizados.getDataNascimento());
        medico.setEndereco(dadosAtualizados.getEndereco());
    }
}
