package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Genero;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Jogo;

@JsonPropertyOrder({"id", "nome", "devId", "genero", "dtLancamento", "notaGeral", "urlCapa"})
public class JogoResponseDTO {

    private Long id;
    private String nome;
    private Long devId;
    private Genero genero;
    private LocalDate dtLancamento;
    private Double notaGeral;
    private String urlCapa;


    public JogoResponseDTO(){}

    public JogoResponseDTO(Jogo entity){
        this.id = entity.getId();
        this.nome = entity.getNmJogo();
        this.devId = entity.getDev().getId();
        this.genero = entity.getGenero();
        this.dtLancamento = entity.getDtLancamento();
        this.notaGeral = entity.getNotaGeral();
        this.urlCapa = entity.getUrlCapa();
    }

    public String getUrlCapa() {
        return urlCapa;
    }

    public void setUrlCapa(String urlCapa) {
        this.urlCapa = urlCapa;
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

    public Genero getGenero() {
        return genero;
    }

    public LocalDate getDtLancamento() {
        return dtLancamento;
    }

    public Double getNotaGeral() {
        return notaGeral;
    }

    public void setDevId(Long devId) {
        this.devId = devId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setDtLancamento(LocalDate dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public void setNotaGeral(Double notaGeral) {
        this.notaGeral = notaGeral;
    }

}