package AVAL_SamBrum_GabPinheiro.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import AVAL_SamBrum_GabPinheiro.entities.Pessoa;

@JsonPropertyOrder({"id", "nome", "cpf", "ator"})
public class PessoaResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Ator ator;

    public PessoaResponseDTO() {
    }

    public PessoaResponseDTO(Pessoa entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.ator = entity.getAtor();
    }

    public String getCpf() {
        return cpf;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

}
