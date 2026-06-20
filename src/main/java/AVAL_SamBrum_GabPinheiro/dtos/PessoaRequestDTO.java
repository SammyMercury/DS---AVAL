package AVAL_SamBrum_GabPinheiro.dtos;

import AVAL_SamBrum_GabPinheiro.entities.Ator;
import jakarta.validation.constraints.NotNull;

public class PessoaRequestDTO {
    
    @NotNull(message = "O campo 'nome' é obrigatório.")
    private String nome;

    @NotNull(message = "O campo 'email' é obrigatório.")
    private String email;

    @NotNull(message = "O campo 'cpf' é obrigatório.")
    private String cpf;

    @NotNull(message = "O campo 'senha' é obrigatório.")
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
