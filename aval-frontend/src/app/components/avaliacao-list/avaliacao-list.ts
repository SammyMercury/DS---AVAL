import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Avaliacao } from '../../models/avaliacao';
import { AvaliacaoService } from '../../services/avaliacao.service';

@Component({
  selector: 'app-avaliacao-list',
  imports: [RouterLink],
  templateUrl: './avaliacao-list.html',
  styleUrl: './avaliacao-list.css',
})
export class AvaliacaoList implements OnInit {

  avaliacoes: Avaliacao[] = [];
  mensagemErro = '';

  constructor(private avaliacaoService: AvaliacaoService) { }

  ngOnInit(): void { 
    this.listar(); 
  }

  listar(): void {
    this.avaliacaoService.listar().subscribe({
      next: (dados) => { 
        this.avaliacoes = dados; 
      },
      error: () => { 
        this.mensagemErro = 'Erro ao carregar avaliações.'; 
      }
    });
  }

  excluir(id: number): void {
    if (!confirm('Deseja realmente excluir esta avaliação?')) {
      return;
    }
    this.avaliacaoService.excluir(id.toString()).subscribe({
      next: () => { 
        this.listar(); 
      },
      error: () => { 
        this.mensagemErro = 'Erro ao excluir avaliação.'; 
      }
    });
  }
}