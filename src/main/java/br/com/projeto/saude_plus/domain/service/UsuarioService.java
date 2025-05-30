package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Usuario;
import br.com.projeto.saude_plus.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> buscarGerente() {
        return usuarioRepository.findAll().stream().findFirst();
    }

    public Usuario atualizarGerente(Long id, Usuario usuarioAtualizado) {
        Usuario gerente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gerente não encontrado"));
        gerente.setNome(usuarioAtualizado.getNome());
        gerente.setCpf(usuarioAtualizado.getCpf());
        gerente.setEmail(usuarioAtualizado.getEmail());
        gerente.setSenha(usuarioAtualizado.getSenha());
        gerente.setSexo(usuarioAtualizado.getSexo());
        gerente.setTelefone(usuarioAtualizado.getTelefone());
        gerente.setDataNascimento(usuarioAtualizado.getDataNascimento());
        gerente.setAtivo(usuarioAtualizado.getAtivo());
        gerente.setRole(usuarioAtualizado.getRole());
        gerente.setEndereco(usuarioAtualizado.getEndereco());
        return usuarioRepository.save(gerente);
    }

    public Usuario salvarGerente(Usuario gerente) {
        return usuarioRepository.save(gerente);
    }
}