package br.com.projeto.saude_plus.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "gerente")
@Getter
@Setter
@NoArgsConstructor
public class Gerente extends Usuario {

    @Column(name = "codigo_autorizacao", nullable = false, length = 32)
    private String codigoAutorizacao;

    @Column(name = "data_inicio_gestao", nullable = false)
    private LocalDate dataInicioGestao;

}
