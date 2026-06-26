import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Jogo } from '../../models/jogo';
import { Genero } from '../../models/genero';
import { Desenvolvedor } from '../../models/desenvolvedor';
import { JogoService } from '../../services/jogo.service';
import { GeneroService } from '../../services/genero.service';
import { DesenvolvedorService } from '../../services/desenvolvedor.service';

@Component({
    selector: 'app-jogo-form',
    imports: [ReactiveFormsModule, RouterLink, CommonModule],
    templateUrl: './jogo-form.html',
    styleUrl: './jogo-form.css',
})
export class JogoForm implements OnInit {

    mensagemErro = '';
    id?: string;
    formJogo: FormGroup;
    generos: Genero[] = [];
    desenvolvedores: Desenvolvedor[] = [];

    constructor(
        private fb: FormBuilder,
        private jogoService: JogoService,
        private generoService: GeneroService,
        private desenvolvedorService: DesenvolvedorService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.formJogo = this.fb.group({
            nmJogo: ['', [Validators.required, Validators.minLength(2)]],
            dtLancamento: ['', Validators.required],
            genero: ['', Validators.required],
            devId: ['', Validators.required]
        });
    }

    ngOnInit(): void {
        this.carregarGeneros();
        this.carregarDesenvolvedores();

        this.id = this.activatedRoute.snapshot.paramMap.get('id') ?? undefined;

        if (this.id) {
            this.jogoService.buscarPorId(this.id).subscribe({
                next: (jogo) => {
                    this.formJogo.patchValue({
                        nmJogo: jogo.nmJogo,
                        dtLancamento: jogo.dtLancamento,
                        genero: jogo.genero,
                        devId: jogo.devId
                    });
                },
                error: () => {
                    this.mensagemErro = 'Erro ao carregar os dados do jogo.';
                }
            });
        }
    }

    carregarGeneros(): void {
        this.generoService.listar().subscribe({
            next: (dados) => { 
                this.generos = dados; 
            },
            error: () => { 
                this.mensagemErro = 'Erro ao carregar gêneros.'; 
            }
        });
    }

    carregarDesenvolvedores(): void {
        this.desenvolvedorService.listar().subscribe({
            next: (dados: Desenvolvedor[]) => { 
                this.desenvolvedores = dados; 
            },
            error: () => { 
                this.mensagemErro = 'Erro ao carregar desenvolvedores.'; 
            }
        });
    }

    salvar(): void {
        if (this.formJogo.invalid) {
            this.formJogo.markAllAsTouched();
            return;
        }

        const jogo: Jogo = this.formJogo.value;
        jogo.id = this.id ? Number(this.id) : undefined;

        this.jogoService.salvar(jogo).subscribe({
            next: () => {
                this.router.navigate(['/jogos']);
            },
            error: () => {
                this.mensagemErro = 'Erro ao salvar jogo.';
            }
        });
    }

    validarCampo(campo: string, erro: string): boolean {
        const controle = this.formJogo.get(campo);
        return !!(controle && controle.touched && controle.hasError(erro));
    }
}