package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nmJogo;

    @Column(nullable = false)
    private LocalDateTime dtLancamento;

    @Column(nullable = false)
    private Double notaGeral;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Dev dev;


    public Jogo(){}


    public Jogo(Long id, String nmJogo, LocalDateTime dtLancamento, Double notaGeral, Dev dev, Genero genero){
        this.id = id;
        this.nmJogo = nmJogo;
        this.dtLancamento = dtLancamento;
        this.notaGeral = notaGeral;
        this.dev = dev;
        this.genero = genero;
    }


    public void setDev(Dev dev) {
        this.dev = dev;
    }

    public Dev getDev() {
        return dev;
    }

    
    public LocalDateTime getDtLancamento() {
        return dtLancamento;
    }
    
    public Genero getGenero() {
        return genero;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getNmJogo() {
        return nmJogo;
    }
    
    public Double getNotaGeral() {
        return notaGeral;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNmJogo(String nmJogo) {
        this.nmJogo = nmJogo;
    }

    public void setNotaGeral(Double notaGeral) {
        this.notaGeral = notaGeral;
    }
    
    public void setDtLancamento(LocalDateTime dtLancamento) {
        this.dtLancamento = dtLancamento;
    }
    
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    

}
