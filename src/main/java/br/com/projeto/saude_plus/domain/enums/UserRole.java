package br.com.projeto.saude_plus.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    MEDIC("Médico"),
    CUSTOMER("Cliente");

    private final String description;

}
