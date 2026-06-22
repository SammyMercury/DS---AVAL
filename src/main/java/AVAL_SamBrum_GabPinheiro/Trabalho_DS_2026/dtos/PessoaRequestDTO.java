package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Ator;
import jakarta.validation.constraints.NotBlank;

public class PessoaRequestDTO {
    
    @NotBlank(message = "O campo 'nome' é obrigatório.")
    private String nome;

    @NotBlank(message = "O campo 'email' é obrigatório.")
    private String email;

    @NotBlank(message = "O campo 'cpf' é obrigatório.")
    private String cpf;

    @NotBlank(message = "O campo 'senha' é obrigatório.")
    private String senha;

    private Ator ator;

    public PessoaRequestDTO(){}

    //getters e setters

    public Ator getAtor() {
        return ator;
    }

    
    public String getCpf() {
        return cpf;
    }
    
    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    
    

}
