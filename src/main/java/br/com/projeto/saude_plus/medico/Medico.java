package br.com.projeto.saude_plus.medico;

import jakarta.persistence.*;

@Entity // Indica que essa classe é uma entidade do banco de dados
@Table(name = "Medicos") // Define o nome da tabela no banco

public class Medico {
    @Id // Indica que esse campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    // Construtor vazio necessário para o JPA
    public Medico() {
    }

    // Construtor com parâmetros
    public Medico(Long id, String nome, String email, String crm, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.crm = crm;
        this.telefone = telefone;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
