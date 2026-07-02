package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.JogoRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.JogoResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Dev;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Jogo;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.BusinessException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.DatabaseException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.ResourceNotFoundException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.DevRepository;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.JogoRepository;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private DevRepository devRepository;

    @Transactional(readOnly = true)
    public List<JogoResponseDTO> listar() {
        List<Jogo> lista = jogoRepository.findAll();
        return lista.stream().map(JogoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public JogoResponseDTO findById(Long id) {
        Jogo entity = jogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado. Id: " + id));
        return new JogoResponseDTO(entity);
    }

    @Transactional
    public JogoResponseDTO insert(JogoRequestDTO dto) {
        // 1. BUSCA A DEV PRIMEIRO (Para ter o objeto correto)
        Dev dev = devRepository.findById(dto.getDevId())
                .orElseThrow(() -> new ResourceNotFoundException("Dev não encontrado. Id: " + dto.getDevId()));

        // 2. Impede duplicidade de cadastro
        if (jogoRepository.existsByNmJogoAndDevAndDtLancamento(dto.getNmJogo(), dev, dto.getDtLancamento())) {
            throw new BusinessException("Jogo já cadastrado.");
        }

        // 3. Instancia e salva o jogo
        Jogo jogo = new Jogo();

        jogo.setNmJogo(dto.getNmJogo());
        jogo.setGenero(dto.getGenero());
        jogo.setDev(dev);
        jogo.setDtLancamento(dto.getDtLancamento());
        jogo.setNotaGeral(dto.getNotaGeral() != null ? dto.getNotaGeral() : 0.0);

        return new JogoResponseDTO(jogoRepository.save(jogo));
    }

    @Transactional
    public JogoResponseDTO update(Long idJogo, JogoRequestDTO dto) {
        // 1. LOCALIZA O JOGO NO BANCO PELO ID
        Jogo jogo = jogoRepository.findById(idJogo)
                .orElseThrow(() -> new ResourceNotFoundException("Jogo não encontrado. ID: " + idJogo));

        // 2. Valida a desenvolvedora caso ela também esteja mudando
        Dev dev = devRepository.findById(dto.getDevId())
                .orElseThrow(() -> new ResourceNotFoundException("Dev não encontrada. ID: " + dto.getDevId()));

        // 3. Copia as novas informações do DTO para a entidade que localizamos
        jogo.setNmJogo(dto.getNmJogo());
        jogo.setGenero(dto.getGenero());
        jogo.setDtLancamento(dto.getDtLancamento());
        jogo.setDev(dev);
        if (dto.getNotaGeral() != null) {
            jogo.setNotaGeral(dto.getNotaGeral());
        }

        // 4. Retorna a resposta mapeada em DTO
        return new JogoResponseDTO(jogoRepository.save(jogo));
    }

    @Transactional
    public void delete(Long idJogo) {
        // 1. Verifica se o jogo realmente existe antes de tentar deletar
        if (!jogoRepository.existsById(idJogo)) {
            throw new ResourceNotFoundException("Jogo não encontrado. ID: " + idJogo);
        }

        // 2. Tenta deletar capturando erros de integridade do banco (ex: jogo com avaliações atreladas)
        try {
            jogoRepository.deleteById(idJogo);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não é possível excluir o jogo pois ele possui avaliações vinculadas.");
        }

    }

    @Transactional(readOnly = true)
    public boolean verificarNome(String nome){
        return jogoRepository.existsByNmJogo(nome);
    }


}