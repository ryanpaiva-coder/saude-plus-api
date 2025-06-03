package br.com.projeto.saude_plus.api.controller;

import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioInputDTO;
import br.com.projeto.saude_plus.api.dto.usuarioDTO.UsuarioOutputDTO;
import br.com.projeto.saude_plus.assembler.UsuarioAssembler;
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

    @Autowired
    private UsuarioAssembler usuarioAssembler;

    @GetMapping("/gerente")
    public ResponseEntity<UsuarioOutputDTO> buscarGerente() {
        return usuarioService.buscarGerente()
                .map(usuarioAssembler::toOutputDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/gerente/{id}")
    public ResponseEntity<UsuarioOutputDTO> atualizarGerente(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioInputDTO usuarioInputDTO) {
        Usuario usuario = usuarioAssembler.toEntity(usuarioInputDTO);
        Usuario gerenteAtualizado = usuarioService.atualizarGerente(id, usuario);
        return ResponseEntity.ok(usuarioAssembler.toOutputDTO(gerenteAtualizado));
    }

    @PostMapping("/gerente")
    public ResponseEntity<UsuarioOutputDTO> salvarGerente(
            @Valid @RequestBody UsuarioInputDTO usuarioInputDTO) {
        Usuario usuario = usuarioAssembler.toEntity(usuarioInputDTO);
        Usuario novoGerente = usuarioService.salvarGerente(usuario);
        return ResponseEntity.ok(usuarioAssembler.toOutputDTO(novoGerente));
    }
}
