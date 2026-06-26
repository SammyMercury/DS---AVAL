export interface Comentario {
    id?: number;
    comentario: string;
    dataRegistro?: string;
    idAvaliacao: number;
    idPessoa: number;
    nomePessoa?: string;
}