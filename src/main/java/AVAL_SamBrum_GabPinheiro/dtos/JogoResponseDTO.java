package AVAL_SamBrum_GabPinheiro.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import AVAL_SamBrum_GabPinheiro.entities.Jogo;

@JsonPropertyOrder({"id", "nome", "devId"})
public class JogoResponseDTO {

    private Long id;
    private String nome;
    private Long devId;


    public JogoResponseDTO(){}

    public JogoResponseDTO(Jogo entity){
        this.id = entity.getId();
        this.nome = entity.getNm_jogo();
        this.devId = entity.getDev().getId();
    }

    public Long getDevId() {
        return devId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

}
