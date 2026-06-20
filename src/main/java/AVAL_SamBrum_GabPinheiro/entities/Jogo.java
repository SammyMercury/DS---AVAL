package AVAL_SamBrum_GabPinheiro.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nm_jogo;

    @Column(nullable = false)
    private Date dt_lancamento;

    @Column(nullable = false)
    private Double notaGeral;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @ManyToOne
    @Column(nullable = false)
    private Dev dev;


    public Jogo(){}


    public Jogo(Long id, String nm_jogo, Date dt_lancamento, Double notaGeral, Dev dev, Genero genero){
        this.id = id;
        this.nm_jogo = nm_jogo;
        this.dt_lancamento = dt_lancamento;
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

    
    public Date getDt_lancamento() {
        return dt_lancamento;
    }
    
    public Genero getGenero() {
        return genero;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getNm_jogo() {
        return nm_jogo;
    }
    
    public Double getNotaGeral() {
        return notaGeral;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNm_jogo(String nm_jogo) {
        this.nm_jogo = nm_jogo;
    }

    public void setNotaGeral(Double notaGeral) {
        this.notaGeral = notaGeral;
    }
    
    public void setDt_lancamento(Date dt_lancamento) {
        this.dt_lancamento = dt_lancamento;
    }
    
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    

}
