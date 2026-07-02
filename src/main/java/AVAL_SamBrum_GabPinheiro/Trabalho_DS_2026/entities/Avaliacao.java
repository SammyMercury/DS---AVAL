package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities;

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
@Table(name = "tb_Avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double nota;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(nullable= false)
    private Pessoa user;

    @ManyToOne
    @JoinColumn(nullable= false)
    private Jogo jogo;



    public Avaliacao(){}


    public Avaliacao(Long id, Double nota, Estado estado, Pessoa user, Jogo jogo){
        this.id = id;
        this.nota = nota;
        this.estado = estado;
        this.user = user;
        this.jogo = jogo;

    }

    public Jogo getJogo() {
        return jogo;
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

    public Pessoa getUser() {
        return user;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    

}
