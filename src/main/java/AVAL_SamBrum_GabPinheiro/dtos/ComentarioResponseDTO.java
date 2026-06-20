package AVAL_SamBrum_GabPinheiro.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import AVAL_SamBrum_GabPinheiro.entities.Comentario;

@JsonPropertyOrder({"id", "userId", "texto", "avaliacaoId"})
public class ComentarioResponseDTO {
    
    private Long id;
    private Long userId;
    private String texto;
    private Long avaliacaoId;

    public ComentarioResponseDTO(){}

    public ComentarioResponseDTO(Comentario entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.texto = entity.getTexto();
        this.avaliacaoId = entity.getAvaliacao().getId();

    }

    public Long getAvaliacaoId() {
        return avaliacaoId;
    }
    public Long getId() {
        return id;
    }
    
    public String getTexto() {
        return texto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setAvaliacaoId(Long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    

}
