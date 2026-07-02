package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.AvaliacaoRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.AvaliacaoResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Avaliacao;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Jogo;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Pessoa;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.BusinessException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.ResourceNotFoundException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.AvaliacaoRepository;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.JogoRepository;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.PessoaRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public List<AvaliacaoResponseDTO> listar() {
        List<Avaliacao> lista = avaliacaoRepository.findAll();
        return lista.stream().map(AvaliacaoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public AvaliacaoResponseDTO findById(Long id) {
        Avaliacao entity = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. Id: " + id));
        return new AvaliacaoResponseDTO(entity);
    }

    @Transactional
    public AvaliacaoResponseDTO insert(Long idJogo, AvaliacaoRequestDTO dto) {
        // 1. Busca o Jogo e a Pessoa
        Jogo jogo = jogoRepository.findById(idJogo)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado. ID: " + idJogo));

        Pessoa pessoa = pessoaRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID: " + dto.getUserId()));

        // Regra de negócio: Apenas contas do tipo USUARIO comum avaliam (Monitores gerenciam o catálogo)
        if (!"USUARIO".equals(pessoa.getAtor().name())) {
            throw new BusinessException("Apenas usuários comuns podem avaliar jogos.");
        }

        // Regra de negócio: Um usuário não pode avaliar o mesmo jogo duas vezes
        if (avaliacaoRepository.existsByJogoAndUser(jogo, pessoa)) {
            throw new BusinessException("Você já avaliou este jogo anteriormente.");
        }

        // 2. Cria a avaliação
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setJogo(jogo); // Certifique-se de ter o setJogo na sua entidade Avaliacao
        avaliacao.setUser(pessoa);
        avaliacao.setNota(dto.getNota());
        avaliacao.setEstado(dto.getEstado());

        avaliacao = avaliacaoRepository.save(avaliacao);

        // 3. Atualiza a nota geral do jogo no catálogo
        recalcularNotaGeralJogo(jogo);

        return new AvaliacaoResponseDTO(avaliacao);
    }

    @Transactional
    public AvaliacaoResponseDTO update(Long idAvaliacao, AvaliacaoRequestDTO dto) {
        // 1. Busca a avaliação existente
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. ID: " + idAvaliacao));

        // Segurança: Garante que quem está editando é o mesmo dono da avaliação
        if (!dto.getUserId().equals(avaliacao.getUser().getId())) {
            throw new BusinessException("Apenas o proprietário da avaliação pode alterá-la.");
        }

        // 2. Atualiza os dados permitidos
        avaliacao.setNota(dto.getNota());
        avaliacao.setEstado(dto.getEstado());

        avaliacao = avaliacaoRepository.save(avaliacao);

        // 3. Recalcula a nota do jogo (pois a nota desta avaliação mudou)
        recalcularNotaGeralJogo(avaliacao.getJogo());

        return new AvaliacaoResponseDTO(avaliacao);
    }

    @Transactional
    public void delete(Long idAvaliacao) {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. ID: " + idAvaliacao));

        Jogo jogoOriginal = avaliacao.getJogo();

        avaliacaoRepository.deleteById(idAvaliacao);

        // Atualiza a nota geral do jogo após a remoção
        recalcularNotaGeralJogo(jogoOriginal);
    }

    /**
     * Método auxiliar privado para recalcular e salvar de forma automatizada 
     * a média das notas de um jogo específico.
     */
    private void recalcularNotaGeralJogo(Jogo jogo) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByJogo(jogo);
        
        if (avaliacoes.isEmpty()) {
            jogo.setNotaGeral(0.0); // Se não houver mais avaliações, zera a nota geral
        } else {
            double soma = avaliacoes.stream().mapToDouble(Avaliacao::getNota).sum();
            double media = soma / avaliacoes.size();
            jogo.setNotaGeral(media);
        }
        
        jogoRepository.save(jogo);
    }
}