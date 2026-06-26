package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.PessoaRequestDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.dtos.PessoaResponseDTO;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Ator;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.entities.Pessoa;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.BusinessException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.DatabaseException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions.ResourceNotFoundException;
import AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.repositories.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional(readOnly = true)
    public List<PessoaResponseDTO> listar() {
        List<Pessoa> lista = pessoaRepository.findAll();
        return lista.stream().map(PessoaResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PessoaResponseDTO findById(Long id) {
        Pessoa entity = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. Id: " + id));
        return new PessoaResponseDTO(entity);
    }

    @Transactional
    public PessoaResponseDTO insert(PessoaRequestDTO dto) {
        // 1. Validação de Duplicidade: CPF
        if (pessoaRepository.existsByCpf(dto.getCpf())) {
            throw new BusinessException("Já existe um usuário cadastrado com este CPF.");
        }

        // 2. Validação de Duplicidade: E-mail
        if (pessoaRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Este e-mail já está em uso.");
        }

        // 3. Instancia a entidade e transfere os dados do DTO
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setEmail(dto.getEmail());
        pessoa.setCpf(dto.getCpf());
        pessoa.setSenha(dto.getSenha()); // Em sistemas reais, aqui aplicaríamos criptografia (BCrypt)

        // Regra de negócio: Se não vier um Ator definido no DTO, o padrão vira USUARIO comum
        if (dto.getAtor() == null) {
            pessoa.setAtor(Ator.USUARIO);
        } else {
            pessoa.setAtor(dto.getAtor());
        }

        return new PessoaResponseDTO(pessoaRepository.save(pessoa));
    }

    @Transactional
    public PessoaResponseDTO update(Long id, PessoaRequestDTO dto) {
        // 1. Busca a pessoa existente que está sendo atualizada
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. ID: " + id));

        // 2. Busca potenciais donos do CPF e Email enviados no DTO
        Pessoa confereCpf = pessoaRepository.findByCpf(dto.getCpf());
        Pessoa confereEmail = pessoaRepository.findByEmail(dto.getEmail());

        // 3. Validação do CPF: Se achou alguém com esse CPF e NÃO é o próprio usuário sendo editado
        if (confereCpf != null && !confereCpf.getId().equals(pessoa.getId())) {
            throw new BusinessException("O CPF informado já está cadastrado em outra conta.");
        }

        // 4. Validação do E-mail: Se achou alguém com esse e-mail e NÃO é o próprio usuário sendo editado
        if (confereEmail != null && !confereEmail.getId().equals(pessoa.getId())) {
            throw new BusinessException("O e-mail informado já está em uso por outra conta.");
        }

        // 5. Atualização dos dados seguros
        pessoa.setNome(dto.getNome());
        pessoa.setEmail(dto.getEmail());
        pessoa.setCpf(dto.getCpf());
        pessoa.setSenha(dto.getSenha());

        if (dto.getAtor() != null) {
            pessoa.setAtor(dto.getAtor());
        }

        return new PessoaResponseDTO(pessoaRepository.save(pessoa));
    }

    @Transactional
    public void delete(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado. ID: " + id);
        }

        try {
            pessoaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Se o usuário já tiver feito avaliações ou comentários, o banco vai travar aqui
            throw new DatabaseException("Não é possível excluir este usuário pois ele possui histórico de interações (avaliações/comentários) no sistema.");
        }
    }
}
