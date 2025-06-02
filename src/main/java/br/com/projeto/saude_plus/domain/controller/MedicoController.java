package br.com.projeto.saude_plus.domain.controller;

import br.com.projeto.saude_plus.domain.model.Medico;
import br.com.projeto.saude_plus.domain.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> cadastrar(@Valid @RequestBody Medico medico) {
        Medico novoMedico = medicoService.cadastrarMedico(medico);
        return ResponseEntity.ok(novoMedico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @Valid @RequestBody Medico medico) {
        Medico medicoAtualizado = medicoService.atualizarMedico(id, medico);
        return ResponseEntity.ok(medicoAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medico);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        List<Medico> medicos = medicoService.listarTodos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Medico>> listarAtivos() {
        List<Medico> medicos = medicoService.listarAtivos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<Medico>> listarDesativados() {
        List<Medico> medicos = medicoService.listarDesativados();
        return ResponseEntity.ok(medicos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        medicoService.desativarMedico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<Medico> buscarPorCrm(@PathVariable String crm) {
        return medicoService.buscarPorCrm(crm)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidade/{nomeEspecialidade}")
    public ResponseEntity<List<Medico>> buscarPorEspecialidade(@PathVariable String nomeEspecialidade) {
        List<Medico> medicos = medicoService.buscarPorEspecialidade(nomeEspecialidade);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Medico>> buscarPorNome(@PathVariable String nome) {
        List<Medico> medicos = medicoService.buscarPorNome(nome);
        return ResponseEntity.ok(medicos);
    }
}
