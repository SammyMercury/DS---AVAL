package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Avaliacao;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    boolean existsByAvaliacao(Avaliacao avaliacao);
}
