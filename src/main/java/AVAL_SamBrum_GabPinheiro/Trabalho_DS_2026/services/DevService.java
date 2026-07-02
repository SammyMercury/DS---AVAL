package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Dev;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.BusinessException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.DatabaseException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.ResourceNotFoundException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.DevRepository;

@Service
public class DevService {

    @Autowired
    private DevRepository devRepository;

    @Transactional(readOnly = true)
    public List<Dev> listar() {
        // Se você tiver um DevResponseDTO, mude o retorno e o mapeamento aqui.
        // Caso use a entidade direta para simplificar:
        return devRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Dev findById(Long id) {
        return devRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Desenvolvedora não encontrada. Id: " + id));
    }

    @Transactional
    public Dev insert(Dev dto) {
        // Impede nomes duplicados no banco
        if (devRepository.existsByNome(dto.getNome())) {
            throw new BusinessException("Já existe uma desenvolvedora cadastrada com este nome.");
        }

        Dev dev = new Dev();
        dev.setNome(dto.getNome());

        return devRepository.save(dev);
    }

    @Transactional
    public Dev update(Long id, Dev dto) {
        // 1. Busca a antiga
        Dev dev = devRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Desenvolvedora não encontrada. ID: " + id));

        // 2. Valida se o novo nome já pertence a OUTRA desenvolvedora
        Dev confereNome = devRepository.findByNome(dto.getNome());
        if (confereNome != null && !confereNome.getId().equals(dev.getId())) {
            throw new BusinessException("Este nome de desenvolvedora já está em uso.");
        }

        dev.setNome(dto.getNome());

        return devRepository.save(dev);
    }

    @Transactional
    public void delete(Long id) {
        if (!devRepository.existsById(id)) {
            throw new ResourceNotFoundException("Desenvolvedora não encontrada. ID: " + id);
        }

        try {
            devRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Se houver algum Jogo associado a esta Dev, o banco de dados vai travar a exclusão aqui
            throw new DatabaseException("Não é possível excluir esta desenvolvedora pois ela possui jogos vinculados no catálogo.");
        }
    }
}