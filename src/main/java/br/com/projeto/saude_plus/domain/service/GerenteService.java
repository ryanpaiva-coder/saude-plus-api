package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Gerente;
import br.com.projeto.saude_plus.domain.model.Role;
import br.com.projeto.saude_plus.domain.repository.GerenteRepository;
import br.com.projeto.saude_plus.domain.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<Gerente> listarTodos() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> buscarPorId(Long id) {
        return gerenteRepository.findById(id);
    }

    @Transactional
    public Gerente salvar(Gerente gerente) {
        gerente.setRole(buscarRoleGerente());
        return gerenteRepository.save(gerente);
    }

    @Transactional
    public void desativar(Long id) {
        Gerente gerente = gerenteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Gerente não encontrado"));
        gerente.setAtivo(false);
        gerente.setDataDesativacao(LocalDateTime.now());
        gerenteRepository.save(gerente);
    }

    private Role buscarRoleGerente() {
        return roleRepository.findByNome("GERENTE")
            .orElseThrow(() -> new RuntimeException("Role GERENTE não encontrada"));
    }
}
