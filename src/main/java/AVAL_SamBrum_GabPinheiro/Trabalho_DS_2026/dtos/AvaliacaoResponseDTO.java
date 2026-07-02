package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Avaliacao;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Estado;




@JsonPropertyOrder({"id", "userId", "jogoId", "estado", "nota"})
public class AvaliacaoResponseDTO {
    

    private Long id;
    private Long userId;
    private Long jogoId;
    private Estado estado;
    private Double nota;

    public AvaliacaoResponseDTO(){}
    
    public AvaliacaoResponseDTO(Avaliacao entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.jogoId = entity.getJogo().getId();
        this.estado = entity.getEstado();
        this.nota = entity.getNota();
    
    }  

    public Estado getEstado() {
        return estado;
    }

    public Long getId() {
        return id;
    }

    public Double getNota() {
        return nota;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getJogoId() {
        return jogoId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setJogoId(Long jogoId) {
        this.jogoId = jogoId;
    }

    
}