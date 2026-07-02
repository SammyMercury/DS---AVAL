package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.PessoaRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.PessoaResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "Endpoints para gerenciamento de usuários")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @Operation(summary = "Listar todas as pessoas")
    public ResponseEntity<List<PessoaResponseDTO>> listar() {
        List<PessoaResponseDTO> lista = pessoaService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pessoa por ID")
    public ResponseEntity<PessoaResponseDTO> findById(@PathVariable Long id) {
        PessoaResponseDTO dto = pessoaService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova pessoa")
    public ResponseEntity<PessoaResponseDTO> insert(@Valid @RequestBody PessoaRequestDTO dto) {
        PessoaResponseDTO newDto = pessoaService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de uma pessoa")
    public ResponseEntity<PessoaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaRequestDTO dto) {
        PessoaResponseDTO updatedDto = pessoaService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma pessoa")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}