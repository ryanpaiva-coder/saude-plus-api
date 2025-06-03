package br.com.projeto.saude_plus.domain.controller;

import br.com.projeto.saude_plus.domain.model.Clinica;
import br.com.projeto.saude_plus.domain.service.ClinicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @GetMapping("/{id}")
    public ResponseEntity<Clinica> buscarClinica(@PathVariable Long id) {
        Clinica clinica = clinicaService.buscarClinica(id);
        return ResponseEntity.ok(clinica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinica> atualizarClinica(
            @PathVariable Long id,
            @Valid @RequestBody Clinica dadosAtualizados) {
        Clinica clinicaAtualizada = clinicaService.atualizarClinica(id, dadosAtualizados);
        return ResponseEntity.ok(clinicaAtualizada);
    }
}
