package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.JogoRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.JogoResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jogos")
@Tag(name = "Jogos", description = "Endpoints para gerenciamento do catálogo de jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    @Operation(summary = "Listar todos os jogos")
    public ResponseEntity<List<JogoResponseDTO>> listar() {
        List<JogoResponseDTO> lista = jogoService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar jogo por ID")
    public ResponseEntity<JogoResponseDTO> findById(@PathVariable Long id) {
        JogoResponseDTO dto = jogoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo jogo")
    public ResponseEntity<JogoResponseDTO> insert(@Valid @RequestBody JogoRequestDTO dto) {
        JogoResponseDTO newDto = jogoService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de um jogo")
    public ResponseEntity<JogoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody JogoRequestDTO dto) {
        JogoResponseDTO updatedDto = jogoService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um jogo do catálogo")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jogoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/verificar-nome")
    @Operation(summary = "Verificar se um jogo já existe pelo nome")
    public ResponseEntity<Boolean> verificarNome(@RequestParam String nome) {
        boolean exists = jogoService.verificarNome(nome);
        return ResponseEntity.ok(exists);
    }
}