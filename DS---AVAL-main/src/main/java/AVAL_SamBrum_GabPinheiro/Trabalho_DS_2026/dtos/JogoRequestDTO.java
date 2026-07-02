package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos;

import java.time.LocalDate;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JogoRequestDTO {
    
    @NotBlank(message = "O campo 'nm_jogo' é obrigatório.")
    private String nmJogo;
    
    @NotNull(message = "O campo 'dt_lancamento' é obrigatório.")
    private LocalDate dtLancamento;
    
    private Double notaGeral = 0.0; 

    @NotNull(message = "O campo 'genero' é obrigatório.")
    private Genero genero;

    @NotNull(message = "O campo 'devId' é obrigatório.")
    private Long devId;

    private String urlCapa;


    public JogoRequestDTO(){}

    public String getUrlCapa() {
        return urlCapa;
    }

    public void setUrlCapa(String urlCapa) {
        this.urlCapa = urlCapa;
    }

    public Long getDevId() {
        return devId;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getNmJogo() {
        return nmJogo;
    }

    public Double getNotaGeral() {
        return notaGeral != null ? notaGeral : 0.0;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
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