package AVAL_SamBrum_GabPinheiro.dtos;

import AVAL_SamBrum_GabPinheiro.entities.Estado;
import jakarta.validation.constraints.NotBlank;

public class AvaliacaoRequestDTO {

    @NotBlank(message = "O campo 'userId' é obrigatório")
    private Long userId;

    private Estado estado;

    @NotBlank(message = "O campo 'nota' é obrigatório")
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
