package AVAL_SamBrum_GabPinheiro.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @ManyToOne
    @Column(nullable = false)
    private Pessoa user;

    @ManyToOne
    @Column(nullable = false)
    private Avaliacao avaliacao;

    public Comentario(){}

    public Comentario(Long id, String texto, Pessoa user, Avaliacao avaliacao){
        this.id = id;
        this.texto = texto;
        this.user = user;
        this.avaliacao = avaliacao;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public Pessoa getUser() {
        return user;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

    
}
