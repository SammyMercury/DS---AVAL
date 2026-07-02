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
import org.springframework.web.bind.annotation.RestController;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.AvaliacaoRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.AvaliacaoResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacoes")
@Tag(name = "Avaliações", description = "Endpoints para gerenciamento de avaliações de jogos")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    @Operation(summary = "Listar todas as avaliações")
    public ResponseEntity<List<AvaliacaoResponseDTO>> listar() {
        List<AvaliacaoResponseDTO> lista = avaliacaoService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar avaliação por ID")
    public ResponseEntity<AvaliacaoResponseDTO> findById(@PathVariable Long id) {
        AvaliacaoResponseDTO dto = avaliacaoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/jogo/{idJogo}")
    @Operation(summary = "Cadastrar uma nova avaliação para um jogo específico (Apenas conta USUARIO)")
    public ResponseEntity<AvaliacaoResponseDTO> insert(
            @PathVariable Long idJogo,
            @Valid @RequestBody AvaliacaoRequestDTO dto) {
        
        AvaliacaoResponseDTO newDto = avaliacaoService.insert(idJogo, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDto);
    }

    @PutMapping("/{idAvaliacao}")
    @Operation(summary = "Atualizar nota ou estado de uma avaliação existente (Apenas o proprietário)")
    public ResponseEntity<AvaliacaoResponseDTO> update(
            @PathVariable Long idAvaliacao,
            @Valid @RequestBody AvaliacaoRequestDTO dto) {
        
        AvaliacaoResponseDTO updatedDto = avaliacaoService.update(idAvaliacao, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{idAvaliacao}")
    @Operation(summary = "Excluir uma avaliação do sistema")
    public ResponseEntity<Void> delete(@PathVariable Long idAvaliacao) {
        avaliacaoService.delete(idAvaliacao);
        return ResponseEntity.noContent().build();
    }
}