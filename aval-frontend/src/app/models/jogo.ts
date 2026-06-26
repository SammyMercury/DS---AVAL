export interface Jogo {
    id?: number;
    titulo: string;
    dataLancamento: string; 
    notaGeral?: number;
    idGenero: number;
    idDesenvolvedor: number;
    nomeGenero?: string;
    nomeDesenvolvedor?: string;
}