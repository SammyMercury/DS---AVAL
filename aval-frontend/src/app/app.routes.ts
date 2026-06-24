import { Routes } from '@angular/router';
import { authGuard } from './guards/auth-guard';
import { Login } from './components/login/login';
import { Principal } from './components/layout/principal/principal';
import { GeneroList } from './components/genero-list/genero-list';
import { GeneroForm } from './components/genero-form/genero-form';
import { JogoList } from './components/jogo-list/jogo-list';
import { JogoForm } from './components/jogo-form/jogo-form';
import { AvaliacaoList } from './components/avaliacao-list/avaliacao-list';
import { AvaliacaoForm } from './components/avaliacao-form/avaliacao-form';
import { ComentarioList } from './components/comentario-list/comentario-list';
import { ComentarioForm } from './components/comentario-form/comentario-form';

export const routes: Routes = [
  {
    path: 'login',
    component: Login
  },
  {
    path: '',
    component: Principal,
    canActivate: [authGuard],
    children: [
      { 
        path: 'generos', 
        component: GeneroList 
      },
      { 
        path: 'generos/cadastrar', 
        component: GeneroForm 
      },
      { 
        path: 'generos/alterar/:id', 
        component: GeneroForm 
      },
      { 
        path: 'jogos', 
        component: JogoList 
      },
      { 
        path: 'jogos/cadastrar', 
        component: JogoForm 
      },
      { 
        path: 'jogos/alterar/:id',
        component: JogoForm 
      },
      { 
        path: 'avaliacoes', 
        component: AvaliacaoList 
      },
      { 
        path: 'avaliacoes/cadastrar', 
        component: AvaliacaoForm 
      },
      { 
        path: 'avaliacoes/alterar/:id', 
        component: AvaliacaoForm 
      },
      { 
        path: 'comentarios', 
        component: ComentarioList 
      },
      { 
        path: 'comentarios/cadastrar', 
        component: ComentarioForm 
      },
      { 
        path: 'comentarios/alterar/:id', 
        component: ComentarioForm 
      },
    ]
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];