package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos;

import java.util.Date;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JogoRequestDTO {
    
    @NotBlank(message = "O campo 'nm_jogo' é obrigatório.")
    private String nmJogo;
    
    @NotNull(message = "O campo 'dt_lancamento' é obrigatório.")
    private Date dtLancamento;
    
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

    public Date getDtLancamento() {
        return dtLancamento;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getNmJogo() {
        return nmJogo;
    }

    public Double getNotaGeral() {
        return notaGeral;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    
    public void setDtLancamento(Date dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setNmJogo(String nmJogo) {
        this.nmJogo = nmJogo;
    }

    public void setNotaGeral(Double notaGeral) {
        this.notaGeral = notaGeral;
    }



}
