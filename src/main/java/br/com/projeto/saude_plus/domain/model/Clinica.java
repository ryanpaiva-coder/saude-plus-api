package br.com.projeto.saude_plus.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "Clinica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Clinica {

    @Id
    @NotBlank
    @Size(max = 14)
    @Column(name = "cnpj_clinica", length = 14)
    @EqualsAndHashCode.Include
    private String cnpjClinica;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome_fantasia", length = 100)
    private String nomeFantasia;

    @Size(max = 11)
    @Column(length = 11)
    private String telefone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
}
