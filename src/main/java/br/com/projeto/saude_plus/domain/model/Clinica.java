package br.com.projeto.saude_plus.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "clinica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clinica")
    @EqualsAndHashCode.Include
    private Long id;

    @Size(max = 14)
    @Column(name = "cnpj_clinica", length = 14)
    private String cnpjClinica;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome_fantasia", length = 100)
    private String nomeFantasia;

    @Size(max = 11)
    @Column(length = 11)
    private String telefone;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
}
