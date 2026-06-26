package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Dev;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long>{

    boolean existsByNmJogoAndDevAndDtLancamento(String nmJogo, Dev dev, LocalDateTime dtLancamento);
    boolean existsByNmJogo(String nmJogo);

}
