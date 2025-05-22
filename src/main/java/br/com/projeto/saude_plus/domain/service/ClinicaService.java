package br.com.projeto.saude_plus.domain.service;

import br.com.projeto.saude_plus.domain.model.Clinica;
import br.com.projeto.saude_plus.domain.repository.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    public Clinica buscarClinica(String cnpj) {
        return clinicaRepository.findById(cnpj)
            .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));
    }

    public Clinica atualizarClinica(String cnpj, Clinica dadosAtualizados) {
        Clinica clinica = buscarClinica(cnpj);
        clinica.setNomeFantasia(dadosAtualizados.getNomeFantasia());
        clinica.setTelefone(dadosAtualizados.getTelefone());
        clinica.setEndereco(dadosAtualizados.getEndereco());
        return clinicaRepository.save(clinica);
    }
}