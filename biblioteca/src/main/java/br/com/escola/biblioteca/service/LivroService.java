package br.com.escola.biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.exception.AutorInesistenteException;
import br.com.escola.biblioteca.exception.LivroNaoEncontradoException;
import br.com.escola.biblioteca.repository.AutorRepository;
import br.com.escola.biblioteca.repository.EditoraRepository;
import br.com.escola.biblioteca.repository.GeneroRepository;
import br.com.escola.biblioteca.repository.LivroRepository;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private GeneroRepository generoRepository;

    public LivroResponseDTO salvandoLivro(LivroRequestDTO dto) {

        if (dto.autorId() == null || dto.editoraId() == null || dto.generoId() == null) {
            throw new ("Livro precisa ter Autor, Editora e Gênero obrigatoriamente.");
        }

        Autor autor = autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new AutorInesistenteException());

        Editora editora = editoraRepository.findById(dto.editoraId())
                .orElseThrow(() -> new("Editora inexistente"));

        Genero genero = generoRepository.findById(dto.generoId())
                .orElseThrow(() -> new ("Gênero inexistente"));

        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setAutor(autor);
        livro.setEditora(editora);
        livro.setGenero(genero);

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

        Autor autor = autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new AutorInesistenteException());

        Editora editora = editoraRepository.findById(dto.editoraId())
                .orElseThrow(() -> new("Editora inexistente"));

        Genero genero = generoRepository.findById(dto.generoId())
                .orElseThrow(() -> new ("Gênero inexistente"));

        livroExistente.setTitulo(dto.titulo());
        livroExistente.setIsbn(dto.isbn());
        livroExistente.setAnoPublicacao(dto.anoPublicacao());
        livroExistente.setAutor(autor);
        livroExistente.setEditora(editora);
        livroExistente.setGenero(genero);

        Livro livroAtualizado = livroRepository.save(livroExistente);
        return LivroResponseDTO.fromEntity(livroAtualizado);
    }

    public void deletar(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        livroRepository.delete(livro);
    }
}










