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

    @GetMapping("/{cnpj}")
    public ResponseEntity<Clinica> buscarClinica(@PathVariable String cnpj) {
        Clinica clinica = clinicaService.buscarClinica(cnpj);
        return ResponseEntity.ok(clinica);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<Clinica> atualizarClinica(
            @PathVariable String cnpj,
            @Valid @RequestBody Clinica dadosAtualizados) {
        Clinica clinicaAtualizada = clinicaService.atualizarClinica(cnpj, dadosAtualizados);
        return ResponseEntity.ok(clinicaAtualizada);
    }
}
