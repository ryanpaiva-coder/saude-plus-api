package br.com.projeto.saude_plus.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
public class Paciente extends Usuario {

    @Id
    @Column(name = "id_paciente")
    private Long id; // Herda o id do Usuario

    @Column(columnDefinition = "TEXT")
    @Size(max = 1000)
    private String descricao;
}
