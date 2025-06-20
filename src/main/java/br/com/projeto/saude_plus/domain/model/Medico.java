package br.com.projeto.saude_plus.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medico")
@Getter
@Setter
@NoArgsConstructor
public class Medico extends Usuario {

    @NotBlank
    @Size(max = 12)
    @Column(length = 12)
    private String crm;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_clinica")
    private Clinica clinica;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;
}
