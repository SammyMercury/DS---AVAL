package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Avaliacao;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Jogo;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Pessoa;


@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
    boolean existsByJogoAndUser(Jogo jogo, Pessoa pessoa);
    List<Avaliacao> findByJogo(Jogo jogo);
}   
