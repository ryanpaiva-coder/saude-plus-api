package br.com.projeto.saude_plus.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    ADMIN("Administrador"),
    MEDIC("Médico"),
    PATIENT("Paciente");

    private final String description;

}
