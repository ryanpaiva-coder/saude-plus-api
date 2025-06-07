package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Especialidade;
import br.com.projeto.saude_plus.domain.repository.EspecialidadeRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Especialidade> listarTodas() {
        return especialidadeRepository.findAll();
    }

    @Transactional
    public Especialidade cadastrar(Especialidade especialidade) {
        especialidadeRepository.findByNome(especialidade.getNome())
            .ifPresent(e -> { throw new RuntimeException("Especialidade já cadastrada."); });
        return especialidadeRepository.save(especialidade);
    }

    public Especialidade buscarPorId(Long id) {
        return especialidadeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
    }
}
