package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.ComentarioRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.ComentarioResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
@Tag(name = "Comentários", description = "Endpoints para gerenciamento de comentários em avaliações")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    @Operation(summary = "Listar todos os comentários")
    public ResponseEntity<List<ComentarioResponseDTO>> listar() {
        List<ComentarioResponseDTO> lista = comentarioService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar comentário por ID")
    public ResponseEntity<ComentarioResponseDTO> findById(@PathVariable Long id) {
        ComentarioResponseDTO dto = comentarioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/avaliacao/{idAvaliacao}")
    @Operation(summary = "Cadastrar um novo comentário para uma avaliação específica")
    public ResponseEntity<ComentarioResponseDTO> insert(
            @PathVariable Long idAvaliacao,
            @Valid @RequestBody ComentarioRequestDTO dto,
            @RequestHeader("X-User-Id") @Parameter(description = "ID do usuário que está comentando") Long idUsuarioQueComenta) {
        
        ComentarioResponseDTO newDto = comentarioService.insert(dto, idUsuarioQueComenta, idAvaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDto);
    }

    @PutMapping("/{idComentario}")
    @Operation(summary = "Atualizar texto de um comentário (Apenas o autor)")
    public ResponseEntity<ComentarioResponseDTO> update(
            @PathVariable Long idComentario,
            @Valid @RequestBody ComentarioRequestDTO dto) {
        
        ComentarioResponseDTO updatedDto = comentarioService.update(idComentario, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{idComentario}")
    @Operation(summary = "Excluir um comentário")
    public ResponseEntity<Void> delete(@PathVariable Long idComentario) {
        comentarioService.delete(idComentario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/verificar-avaliacao/{idAvaliacao}")
    @Operation(summary = "Verificar se existem comentários vinculados a uma avaliação")
    public ResponseEntity<Boolean> verificarAvaliacao(@PathVariable Long idAvaliacao) {
        boolean exists = comentarioService.verificarAvaliacao(idAvaliacao);
        return ResponseEntity.ok(exists);
    }
}