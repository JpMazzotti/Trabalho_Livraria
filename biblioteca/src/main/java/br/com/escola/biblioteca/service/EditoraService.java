package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.EditoraRequestDTO;
import br.com.escola.biblioteca.dto.EditoraResponseDTO;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.exception.EditoraNaoEncontradaException;
import br.com.escola.biblioteca.exception.ExclusaoNaoPermitidaException;
import br.com.escola.biblioteca.repository.EditoraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditoraService {
    private final EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    @Transactional(readOnly = true)
    public List<EditoraResponseDTO> listarTodas() {
        return editoraRepository.findAll()
                .stream()
                .map(EditoraResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public EditoraResponseDTO buscarPorId(Long id) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(EditoraNaoEncontradaException::new);
        return EditoraResponseDTO.fromEntity(editora);
    }

    @Transactional
    public EditoraResponseDTO cadastrar(EditoraRequestDTO dto) {
        Editora editora = new Editora();
        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());
        editora.setEstado(dto.estado());
        return EditoraResponseDTO.fromEntity(editoraRepository.save(editora));
    }

    @Transactional
    public EditoraResponseDTO atualizar(Long id, EditoraRequestDTO dto) {
        Editora editoraExistente = editoraRepository.findById(id)
                .orElseThrow(EditoraNaoEncontradaException::new);

        editoraExistente.setNome(dto.nome());
        editoraExistente.setCnpj(dto.cnpj());
        editoraExistente.setEstado(dto.estado());

        return EditoraResponseDTO.fromEntity(editoraRepository.save(editoraExistente));
    }

    @Transactional
    public void deletar(Long id) {
        Editora editora = editoraRepository.findById(id)
                .orElseThrow(EditoraNaoEncontradaException::new);

        if (!editora.getLivros().isEmpty()) {
            throw new ExclusaoNaoPermitidaException("Não é possível excluir a editora pois existem livros vinculados a ela.");
        }

        editoraRepository.delete(editora);
    }
}
