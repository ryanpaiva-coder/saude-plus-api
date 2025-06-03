package br.com.projeto.saude_plus.domain.controller;

import br.com.projeto.saude_plus.domain.enums.StatusConsulta;
import br.com.projeto.saude_plus.domain.model.Consulta;
import br.com.projeto.saude_plus.domain.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> agendarConsulta(@Valid @RequestBody Consulta consulta) {
        Consulta novaConsulta = consultaService.agendarConsulta(consulta);
        return ResponseEntity.ok(novaConsulta);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        List<Consulta> consultas = consultaService.listarTodas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarPorId(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<List<Consulta>> listarPorMedico(@PathVariable Long idMedico) {
        List<Consulta> consultas = consultaService.listarPorMedico(idMedico);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Consulta>> listarPorPaciente(@PathVariable Long idPaciente) {
        List<Consulta> consultas = consultaService.listarPorPaciente(idPaciente);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Consulta>> listarPorStatus(@PathVariable StatusConsulta status) {
        List<Consulta> consultas = consultaService.listarPorStatus(status);
        return ResponseEntity.ok(consultas);
    }

    @PutMapping("/{id}/desmarcar")
    public ResponseEntity<Consulta> desmarcarConsulta(@PathVariable Long id) {
        Consulta consulta = consultaService.desmarcarConsulta(id);
        return ResponseEntity.ok(consulta);
    }

    @GetMapping("/medico/{idMedico}/futuras")
    public ResponseEntity<List<Consulta>> listarConsultasFuturasMedico(@PathVariable Long idMedico) {
        List<Consulta> consultas = consultaService.listarConsultasFuturasMedico(idMedico);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/paciente/{idPaciente}/futuras")
    public ResponseEntity<List<Consulta>> listarConsultasFuturasPaciente(@PathVariable Long idPaciente) {
        List<Consulta> consultas = consultaService.listarConsultasFuturasPaciente(idPaciente);
        return ResponseEntity.ok(consultas);
    }
}
