package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.LoginRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.PessoaResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoint de login para monitores e usuários")
public class AuthController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/login")
    @Operation(summary = "Autenticar pelo e-mail e senha cadastrados")
    public ResponseEntity<PessoaResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        PessoaResponseDTO pessoa = pessoaService.autenticar(dto);
        return ResponseEntity.ok(pessoa);
    }
}