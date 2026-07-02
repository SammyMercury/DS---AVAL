package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Ator;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Pessoa;

@JsonPropertyOrder({"id", "nome", "email", "cpf", "ator"})
public class PessoaResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private Ator ator;

    public PessoaResponseDTO() {
    }
    
    public PessoaResponseDTO(Pessoa entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.ator = entity.getAtor();
    }

    public Ator getAtor() {
        return ator;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }
}