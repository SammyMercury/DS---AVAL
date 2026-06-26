import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Comentario } from '../../models/comentario';
import { Avaliacao } from '../../models/avaliacao';
import { Pessoa } from '../../models/pessoa';
import { ComentarioService } from '../../services/comentario.service';
import { AvaliacaoService } from '../../services/avaliacao.service';
import { PessoaService } from '../../services/pessoa.service';

@Component({
    selector: 'app-comentario-form',
    imports: [ReactiveFormsModule, RouterLink, CommonModule],
    templateUrl: './comentario-form.html',
    styleUrl: './comentario-form.css',
})
export class ComentarioForm implements OnInit {

    mensagemErro = '';
    id?: string;
    formComentario: FormGroup;
    avaliacoes: Avaliacao[] = [];
    pessoas: Pessoa[] = [];

    constructor(
        private fb: FormBuilder,
        private comentarioService: ComentarioService,
        private avaliacaoService: AvaliacaoService,
        private pessoaService: PessoaService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.formComentario = this.fb.group({
            comentario: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(500)]],
            idAvaliacao: ['', Validators.required],
            idPessoa: ['', Validators.required]
        });
    }

    ngOnInit(): void {
        this.carregarAvaliacoes();
        this.carregarPessoas();

        this.id = this.activatedRoute.snapshot.paramMap.get('id') ?? undefined;

        if (this.id) {
            this.comentarioService.buscarPorId(this.id).subscribe({
                next: (comentario: Comentario) => {
                    this.formComentario.patchValue({
                        comentario: comentario.comentario,
                        idAvaliacao: comentario.idAvaliacao,
                        idPessoa: comentario.idPessoa
                    });
                },
                error: () => {
                    this.mensagemErro = 'Erro ao carregar os dados do comentário.';
                }
            });
        }
    }

    carregarAvaliacoes(): void {
        this.avaliacaoService.listar().subscribe({
            next: (dados: Avaliacao[]) => { 
                this.avaliacoes = dados; 
            },
            error: () => { 
                this.mensagemErro = 'Erro ao carregar avaliações.'; 
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
        if (this.formComentario.invalid) {
            this.formComentario.markAllAsTouched();
            return;
        }

        const comentario: Comentario = this.formComentario.value;
        comentario.id = this.id ? Number(this.id) : undefined;

        this.comentarioService.salvar(comentario).subscribe({
            next: () => {
                this.router.navigate(['/comentarios']);
            },
            error: () => {
                this.mensagemErro = 'Erro ao salvar comentário.';
            }
        });
    }

    validarCampo(campo: string, erro: string): boolean {
        const controle = this.formComentario.get(campo);
        return !!(controle && controle.touched && controle.hasError(erro));
    }
}