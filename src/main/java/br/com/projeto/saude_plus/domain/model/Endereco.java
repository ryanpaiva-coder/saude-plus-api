package br.com.projeto.saude_plus.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100)
    private String logradouro;

    @NotBlank
    @Size(max = 10)
    @Column(length = 10)
    private String numero;

    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String bairro;

    @NotBlank
    @Size(max = 8)
    @Column(length = 8)
    private String cep;

    @NotBlank
    @Size(max = 50)
    @Column(length = 50)
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(length = 2)
    private String estado;
}
