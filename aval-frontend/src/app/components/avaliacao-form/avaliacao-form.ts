import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Avaliacao } from '../../models/avaliacao';
import { Jogo } from '../../models/jogo';
import { Pessoa } from '../../models/pessoa';
import { AvaliacaoService } from '../../services/avaliacao.service';
import { JogoService } from '../../services/jogo.service';
import { PessoaService } from '../../services/pessoa.service';

@Component({
    selector: 'app-avaliacao-form',
    imports: [ReactiveFormsModule, RouterLink, CommonModule],
    templateUrl: './avaliacao-form.html',
    styleUrl: './avaliacao-form.css',
})
export class AvaliacaoForm implements OnInit {

    mensagemErro = '';
    id?: string;
    formAvaliacao: FormGroup;
    jogos: Jogo[] = [];
    pessoas: Pessoa[] = [];

    constructor(
        private fb: FormBuilder,
        private avaliacaoService: AvaliacaoService,
        private jogoService: JogoService,
        private pessoaService: PessoaService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.formAvaliacao = this.fb.group({
            nota: ['', [Validators.required, Validators.min(0), Validators.max(5)]],
            idJogo: ['', Validators.required],
            idPessoa: ['', Validators.required]
        });
    }

    ngOnInit(): void {
        this.carregarJogos();
        this.carregarPessoas();

        this.id = this.activatedRoute.snapshot.paramMap.get('id') ?? undefined;

        if (this.id) {
            this.avaliacaoService.buscarPorId(this.id).subscribe({
                next: (avaliacao: Avaliacao) => {
                    this.formAvaliacao.patchValue({
                        nota: avaliacao.nota,
                        idJogo: avaliacao.idJogo,
                        idPessoa: avaliacao.idPessoa
                    });
                },
                error: () => {
                    this.mensagemErro = 'Erro ao carregar os dados da avaliação.';
                }
            });
        }
    }
    carregarJogos(): void {
        this.jogoService.listar().subscribe({
            next: (dados: Jogo[]) => { 
                this.jogos = dados; 
            },
            error: () => { 
                this.mensagemErro = 'Erro ao carregar jogos.'; 
            }
        });
    }

    carregarPessoas(): void {
        this.pessoaService.listar().subscribe({
            next: (dados: Pessoa[]) => { 
                this.pessoas = dados; 
            },
            error: () => { 
                this.mensagemErro = 'Erro ao carregar pessoas.'; 
            }
        });
    }

    salvar(): void {
        if (this.formAvaliacao.invalid) {
            this.formAvaliacao.markAllAsTouched();
            return;
        }

        const avaliacao: Avaliacao = this.formAvaliacao.value;
        avaliacao.id = this.id ? Number(this.id) : undefined;

        this.avaliacaoService.salvar(avaliacao).subscribe({
            next: () => {
                this.router.navigate(['/avaliacoes']);
            },
            error: () => {
                this.mensagemErro = 'Erro ao salvar avaliação.';
            }
        });
    }

    validarCampo(campo: string, erro: string): boolean {
        const controle = this.formAvaliacao.get(campo);
        return !!(controle && controle.touched && controle.hasError(erro));
    }
}