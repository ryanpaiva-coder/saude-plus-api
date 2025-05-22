package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Medico;
import br.com.projeto.saude_plus.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrarMedico(Medico medico) {
        medico.setAtivo(true);
        return medicoRepository.save(medico);
    }

    public Medico atualizarMedico(Long id, Medico dadosAtualizados) {
        Medico medico = buscarPorId(id);
        medico.setNome(dadosAtualizados.getNome());
        medico.setCrm(dadosAtualizados.getCrm());
        medico.setEspecialidade(dadosAtualizados.getEspecialidade());
        medico.setClinica(dadosAtualizados.getClinica());
        medico.setEmail(dadosAtualizados.getEmail());
        medico.setTelefone(dadosAtualizados.getTelefone());
        medico.setSexo(dadosAtualizados.getSexo());
        medico.setDataNascimento(dadosAtualizados.getDataNascimento());
        medico.setEndereco(dadosAtualizados.getEndereco());
        return medicoRepository.save(medico);
    }

    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
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

    public void desativarMedico(Long id) {
        Medico medico = buscarPorId(id);
        medico.setAtivo(false);
        medicoRepository.save(medico);
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
}
