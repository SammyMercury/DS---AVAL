package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.ComentarioRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.ComentarioResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Ator;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Avaliacao;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Comentario;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Pessoa;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.BusinessException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.DatabaseException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.ResourceNotFoundException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.AvaliacaoRepository;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.ComentarioRepository;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.PessoaRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional(readOnly = true)
    public List<ComentarioResponseDTO> listar() {
        List<Comentario> lista = comentarioRepository.findAll();
        return lista.stream().map(ComentarioResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ComentarioResponseDTO findById(Long id) {
        Comentario entity = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario não encontrado. Id: " + id));
        return new ComentarioResponseDTO(entity);
    }

    @Transactional
    public ComentarioResponseDTO insert(ComentarioRequestDTO dto, Long idUsuarioQueComenta, Long idAvaliacao) {
        Pessoa autorDoComentario = pessoaRepository.findById(idUsuarioQueComenta)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID: " + idUsuarioQueComenta));

        Avaliacao avaliacaoAlvo = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. ID: " + idAvaliacao));

        // Regra: Precisa ser USUARIO comum para interagir
        if (!"USUARIO".equals(autorDoComentario.getAtor().name())) {
            throw new BusinessException("Apenas contas de usuários comuns podem realizar comentários.");
        }

        Comentario comentario = new Comentario();
        comentario.setUser(autorDoComentario);
        comentario.setAvaliacao(avaliacaoAlvo);
        comentario.setTexto(dto.getTexto());

        return new ComentarioResponseDTO(comentarioRepository.save(comentario));
    }

    @Transactional
    public ComentarioResponseDTO update(Long idComentario, ComentarioRequestDTO dto) {
        // 1. Busca o comentário que será alterado
        Comentario comentario = comentarioRepository.findById(idComentario)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario não encontrado. ID: " + idComentario));

        // 2. Garante que quem está tentando alterar é o real dono do comentário
        if (!dto.getUserId().equals(comentario.getUser().getId())) {
            throw new BusinessException("Apenas o autor do comentário pode alterá-lo.");
        }

        // 3. Atualiza apenas o que é permitido (o texto)
        comentario.setTexto(dto.getTexto());

        return new ComentarioResponseDTO(comentarioRepository.save(comentario));
    }

    @Transactional
    public void delete(Long idComentario, Long idAutor) {
        Pessoa pessoa = pessoaRepository.findById(idAutor)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID: " + idAutor));

        Comentario comentario = comentarioRepository.findById(idComentario)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario não encontrado. ID: " + idComentario));

        boolean isAutor = idAutor.equals(comentario.getUser().getId());
        boolean isMonitor = pessoa.getAtor() == Ator.MONITOR;

        if (!isAutor && !isMonitor) {
            throw new BusinessException("Apenas o autor do comentário e moderadores podem removê-lo.");
        }

        try {
            comentarioRepository.deleteById(idComentario);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não é possível excluir o comentario.");
        }

    }

    @Transactional(readOnly = true)
    public boolean verificarAvaliacao(Long idAvaliacao) {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. ID: " + idAvaliacao));

        return comentarioRepository.existsByAvaliacao(avaliacao);
    }

}
