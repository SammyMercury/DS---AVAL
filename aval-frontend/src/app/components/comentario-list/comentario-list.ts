import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Comentario } from '../../models/comentario';
import { ComentarioService } from '../../services/comentario.service';

@Component({
  selector: 'app-comentario-list',
  imports: [RouterLink],
  templateUrl: './comentario-list.html',
  styleUrl: './comentario-list.css',
})
export class ComentarioList implements OnInit {

  comentarios: Comentario[] = [];
  mensagemErro = '';

  constructor(private comentarioService: ComentarioService) { }

  ngOnInit(): void { this.listar(); }

  listar(): void {
    this.comentarioService.listar().subscribe({
      next: (dados) => { 
        this.comentarios = dados; 
      },
      error: () => { 
        this.mensagemErro = 'Erro ao carregar comentários.'; 
      }
    });
  }

  excluir(id: number): void {
    if (!confirm('Deseja realmente excluir este comentário?')) {
      return;
    }
    this.comentarioService.excluir(id.toString()).subscribe({
      next: () => { 
        this.listar(); 
      },
      error: () => { 
        this.mensagemErro = 'Erro ao excluir comentário.'; 
      }
    });
  }
}