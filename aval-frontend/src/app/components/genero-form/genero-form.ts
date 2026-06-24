import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, ActivatedRoute } from '@angular/router';
import { Genero } from '../../models/genero';
import { GeneroService } from '../../services/genero.service';

@Component({
    selector: 'app-genero-form',
    imports: [ReactiveFormsModule, RouterLink],
    templateUrl: './genero-form.html',
    styleUrl: './genero-form.css',
})
export class GeneroForm implements OnInit {

    mensagemErro = '';
    id?: string;
    formGenero: FormGroup;

    constructor(
        private fb: FormBuilder,
        private generoService: GeneroService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.formGenero = this.fb.group({
            descricao: ['', [Validators.required, Validators.minLength(2)]]
        });
    }

    ngOnInit(): void {
        this.id = this.activatedRoute.snapshot.paramMap.get('id') ?? undefined;

        if (this.id) {
            this.generoService.buscarPorId(this.id).subscribe({
                next: (dados) => {
                    this.formGenero.patchValue(dados);
                },
                error: () => {
                    this.mensagemErro = 'Erro ao carregar os dados do gênero.';
                }
            });
        }
    }

    salvar(): void {
        if (this.formGenero.invalid) {
            this.formGenero.markAllAsTouched();
            return;
        }

        const genero: Genero = this.formGenero.value;
        genero.id = this.id ? Number(this.id) : undefined;

        this.generoService.salvar(genero).subscribe({
            next: () => { 
                this.router.navigate(['/generos']); 
            },
            error: () => { 
                this.mensagemErro = 'Erro ao salvar gênero.'; 
            }
        });
    }

    validarCampo(campo: string, erro: string): boolean {
        const controle = this.formGenero.get(campo);
        return !!(controle && controle.touched && controle.hasError(erro));
    }
}