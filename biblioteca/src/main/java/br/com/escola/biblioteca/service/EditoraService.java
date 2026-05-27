package br.com.escola.biblioteca.service;

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
    public List<Editora> listarTodas() {
        return editoraRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Editora buscarPorId(Long id) {
        return editoraRepository.findById(id)
            .orElseThrow(() -> new EditoraNaoEncontradaException());
    }

    @Transactional
    public Editora cadastrar(Editora editora) {
        return editoraRepository.save(editora);
    }

    @Transactional
    public Editora atualizar(Long id, Editora editoraAtualizada) {
        Editora editoraExistente = buscarPorId(id); 
        editoraExistente.setNome(editoraAtualizada.getNome());
        editoraExistente.setCnpj(editoraAtualizada.getCnpj());
        editoraExistente.setEstado(editoraAtualizada.getEstado());
        return editoraRepository.save(editoraExistente);
    }

    @Transactional
    public void deletar(Long id) {
        Editora editora = editoraRepository.findById(id)
            .orElseThrow(() -> new EditoraNaoEncontradaException());

        if (!editora.getLivros().isEmpty()) {
            throw new ExclusaoNaoPermitidaException("Não é possível excluir a editora pois existem livros vinculados a ela.");
        }

        editoraRepository.delete(editora);
    }
}