package br.com.projeto.saude_plus.domain.controller;

import br.com.projeto.saude_plus.domain.model.Paciente;
import br.com.projeto.saude_plus.domain.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@Valid @RequestBody Paciente paciente) {
        Paciente novoPaciente = pacienteService.cadastrarPaciente(paciente);
        return ResponseEntity.ok(novoPaciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        Paciente pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Paciente>> listarAtivos() {
        List<Paciente> pacientes = pacienteService.listarAtivos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<Paciente>> listarDesativados() {
        List<Paciente> pacientes = pacienteService.listarDesativados();
        return ResponseEntity.ok(pacientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        pacienteService.desativarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPorEmail(@PathVariable String email) {
        return pacienteService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Paciente> buscarPorCpf(@PathVariable String cpf) {
        return pacienteService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Paciente>> buscarPorNome(@PathVariable String nome) {
        List<Paciente> pacientes = pacienteService.buscarPorNome(nome);
        return ResponseEntity.ok(pacientes);
    }
}
