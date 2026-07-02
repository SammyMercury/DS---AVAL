package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "O campo 'email' é obrigatório.")
    private String email;

    @NotBlank(message = "O campo 'senha' é obrigatório.")
    private String senha;

    public LoginRequestDTO(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}