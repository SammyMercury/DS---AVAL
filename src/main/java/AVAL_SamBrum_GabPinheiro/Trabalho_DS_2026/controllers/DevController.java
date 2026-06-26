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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Dev;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services.DevService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/desenvolvedoras")
@Tag(name = "Desenvolvedoras", description = "Endpoints para gerenciamento de estúdios e desenvolvedoras de jogos")
public class DevController {

    @Autowired
    private DevService devService;

    @GetMapping
    @Operation(summary = "Listar todas as desenvolvedoras")
    public ResponseEntity<List<Dev>> listar() {
        List<Dev> lista = devService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar desenvolvedora por ID")
    public ResponseEntity<Dev> findById(@PathVariable Long id) {
        Dev dev = devService.findById(id);
        return ResponseEntity.ok(dev);
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova desenvolvedora (Requer privilégios de MONITOR)")
    public ResponseEntity<Dev> insert(
            @Valid @RequestBody Dev dto,
            @RequestHeader("X-User-Id") @Parameter(description = "ID do usuário MONITOR realizando a ação") Long idPessoaAutenticada) {
        
        Dev newDev = devService.insert(dto, idPessoaAutenticada);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDev);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de uma desenvolvedora (Requer privilégios de MONITOR)")
    public ResponseEntity<Dev> update(
            @PathVariable Long id,
            @Valid @RequestBody Dev dto,
            @RequestHeader("X-User-Id") @Parameter(description = "ID do usuário MONITOR realizando a ação") Long idPessoaAutenticada) {
        
        Dev updatedDev = devService.update(id, dto, idPessoaAutenticada);
        return ResponseEntity.ok(updatedDev);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma desenvolvedora do catálogo (Requer privilégios de MONITOR)")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") @Parameter(description = "ID do usuário MONITOR realizando a ação") Long idPessoaAutenticada) {
        
        devService.delete(id, idPessoaAutenticada);
        return ResponseEntity.noContent().build();
    }
}