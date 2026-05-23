package br.com.escola.biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.exception.AutorInesistenteException;
import br.com.escola.biblioteca.exception.LivroNaoEncontradoException;
import br.com.escola.biblioteca.exception.LivroSemAutorException;
import br.com.escola.biblioteca.exception.LivroTituloNaoNuloException;
import br.com.escola.biblioteca.repository.AutorRepository;
import br.com.escola.biblioteca.repository.LivroRepository;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public LivroResponseDTO salvandoLivro(LivroRequestDTO dto) {
        
        if (dto.autorId() == null) {
            throw new LivroSemAutorException();
        }

        Autor autor = autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new AutorInesistenteException());

        if (dto.titulo() == null || dto.titulo().trim().isEmpty()) {
            throw new LivroTituloNaoNuloException();
        }

        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setGenero(dto.genero());
        livro.setAutor(autor);

        Livro livroSalvo = livroRepository.save(livro);

        return LivroResponseDTO.fromEntity(livroSalvo);
    }

    public List<LivroResponseDTO> buscarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
        
        return LivroResponseDTO.fromEntity(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        if (dto.autorId() == null) {
            throw new LivroSemAutorException();
        }

        Autor autor = autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new AutorInesistenteException());

        if (dto.titulo() == null || dto.titulo().trim().isEmpty()) {
            throw new LivroTituloNaoNuloException();
        }

        livroExistente.setTitulo(dto.titulo());
        livroExistente.setIsbn(dto.isbn());
        livroExistente.setAnoPublicacao(dto.anoPublicacao());
        livroExistente.setGenero(dto.genero());
        livroExistente.setAutor(autor);

        Livro livroAtualizado = livroRepository.save(livroExistente);
        return LivroResponseDTO.fromEntity(livroAtualizado);
    }

    public void deletar(Long id) {
        
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
        
        livroRepository.delete(livro);
    }
}










