export interface Avaliacao {
    id?: number;
    dataRegistro?: string;
    nota: number;
    idJogo: number;
    idPessoa: number;
    tituloJogo?: string;
    nomePessoa?: string;
}