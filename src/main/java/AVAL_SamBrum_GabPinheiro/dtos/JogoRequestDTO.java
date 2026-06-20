package AVAL_SamBrum_GabPinheiro.dtos;

import java.util.Date;

import AVAL_SamBrum_GabPinheiro.entities.Genero;
import jakarta.validation.constraints.NotNull;

public class JogoRequestDTO {
    
    @NotNull(message = "O campo 'nm_jogo' é obrigatório.")
    private String nm_jogo;
    
    @NotNull(message = "O campo 'dt_lancamento' é obrigatório.")
    private Date dt_lancamento;
    
    @NotNull(message = "O campo 'notaGeral' é obrigatório.")
    private Double notaGeral;

    @NotNull(message = "O campo 'genero' é obrigatório.")
    private Genero genero;

    @NotNull(message = "O campo 'devId' é obrigatório.")
    private Long devId;


    public JogoRequestDTO(){}

    public Long getDevId() {
        return devId;
    }

    public Date getDt_lancamento() {
        return dt_lancamento;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getNm_jogo() {
        return nm_jogo;
    }

    public Double getNotaGeral() {
        return notaGeral;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    
    public void setDt_lancamento(Date dt_lancamento) {
        this.dt_lancamento = dt_lancamento;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setNm_jogo(String nm_jogo) {
        this.nm_jogo = nm_jogo;
    }

    public void setNotaGeral(Double notaGeral) {
        this.notaGeral = notaGeral;
    }



}
