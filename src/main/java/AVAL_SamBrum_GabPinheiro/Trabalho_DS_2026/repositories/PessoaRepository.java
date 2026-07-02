package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Pessoa;




@Repository
public interface  PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Com isso, métodos como findAll(), findById() e save() já ficam disponíveis automaticamente!
    // Ao herdar JpaRepository<Pessoa, Long>, o Spring nos dá de graça:
    // .save() -> Insere ou atualiza no banco
    // .findAll() -> Faz um SELECT *
    // .findById() -> Busca por ID
    // .deleteById() -> Apaga do banco

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    Pessoa findByCpf(String cpf);
    Pessoa findByEmail(String email);

}
