package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Estado;
import jakarta.validation.constraints.NotNull;

public class AvaliacaoRequestDTO {

    @NotNull(message = "O campo 'userId' é obrigatório")
    private Long userId;
    private Estado estado;
    
    @NotNull(message = "O campo 'nota' é obrigatório")
    private Double nota;

    public AvaliacaoRequestDTO(){}

    public Double getNota() {
        return nota;
    }

    public Estado getEstado() {
        return estado;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }


}
