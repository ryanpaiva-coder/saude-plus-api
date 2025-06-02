package br.com.projeto.saude_plus.domain.controller;

import br.com.projeto.saude_plus.domain.model.Especialidade;
import br.com.projeto.saude_plus.domain.service.EspecialidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping
    public ResponseEntity<List<Especialidade>> listarTodas() {
        List<Especialidade> especialidades = especialidadeService.listarTodas();
        return ResponseEntity.ok(especialidades);
    }

    @PostMapping
    public ResponseEntity<Especialidade> cadastrar(@Valid @RequestBody Especialidade especialidade) {
        Especialidade nova = especialidadeService.cadastrar(especialidade);
        return ResponseEntity.ok(nova);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscarPorId(@PathVariable Long id) {
        Especialidade especialidade = especialidadeService.buscarPorId(id);
        return ResponseEntity.ok(especialidade);
    }
}
