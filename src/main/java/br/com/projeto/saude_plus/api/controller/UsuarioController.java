package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.domain.model.Usuario;
import br.com.projeto.saude_plus.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/gerente")
    public ResponseEntity<Usuario> buscarGerente() {
        return usuarioService.buscarGerente()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/gerente/{id}")
    public ResponseEntity<Usuario> atualizarGerente(
        @PathVariable Long id,
        @Valid @RequestBody Usuario usuario
    ) {
        Usuario gerenteAtualizado = usuarioService.atualizarGerente(id, usuario);
        return ResponseEntity.ok(gerenteAtualizado);
    }

    @PostMapping("/gerente")
    public ResponseEntity<Usuario> salvarGerente(
        @Valid @RequestBody Usuario gerente
    ) {
        Usuario novoGerente = usuarioService.salvarGerente(gerente);
        return ResponseEntity.ok(novoGerente);
    }
}
