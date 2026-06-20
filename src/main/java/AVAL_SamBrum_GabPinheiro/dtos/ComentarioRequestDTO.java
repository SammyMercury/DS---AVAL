package AVAL_SamBrum_GabPinheiro.dtos;

import jakarta.validation.constraints.NotNull;

public class ComentarioRequestDTO {

    @NotNull(message = "O campo 'texto' é obrigatório")
    private String texto;

    @NotNull(message = "O campo 'userId' é obrigatório")
    private Long userId;

    @NotNull(message = "O campo 'avaliacaoId' é obrigatório")
    private Long avaliacaoId;


    public ComentarioRequestDTO(){}

    public Long getAvaliacaoId() {
        return avaliacaoId;
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

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
