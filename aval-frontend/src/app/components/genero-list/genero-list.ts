import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Genero } from '../../models/genero';
import { GeneroService } from '../../services/genero.service';

@Component({
    selector: 'app-genero-list',
    imports: [RouterLink],
    templateUrl: './genero-list.html',
    styleUrl: './genero-list.css',
})
export class GeneroList implements OnInit {

    generos: Genero[] = [];
    mensagemErro = '';

    constructor(private generoService: GeneroService) { }

    ngOnInit(): void {
        this.listar();
    }

    listar(): void {
        this.generoService.listar().subscribe({
            next: (dados) => {
                this.generos = dados;
            },
            error: () => {
                this.mensagemErro = 'Erro ao carregar a lista de gêneros.';
            }
        });
    }

    excluir(id: number): void {
        if (!confirm('Deseja realmente excluir este gênero?')) {
            return;
        }
        this.generoService.excluir(id.toString()).subscribe({
            next: () => {
                this.listar();
            },
            error: () => {
                this.mensagemErro = 'Erro ao excluir gênero.';
            }
        });
    }
}