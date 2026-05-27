package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.GeneroRequestDTO;
import br.com.escola.biblioteca.dto.GeneroResponseDTO;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.exception.GeneroNaoEncontradoException;
import br.com.escola.biblioteca.repository.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public GeneroResponseDTO criar(GeneroRequestDTO dto) {
        Genero genero = new Genero();
        genero.setNome(dto.nome());
        genero.setSigla(dto.sigla().toUpperCase());

        Genero salvo = generoRepository.save(genero);
        return new GeneroResponseDTO(salvo.getId(), salvo.getNome(), salvo.getSigla());
    }

    public List<GeneroResponseDTO> listarTodos() {
        return generoRepository.findAll()
                .stream()
                .map(g -> new GeneroResponseDTO(g.getId(), g.getNome(), g.getSigla()))
                .collect(Collectors.toList());
    }

    public GeneroResponseDTO buscarPorId(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new GeneroNaoEncontradoException());
        return new GeneroResponseDTO(genero.getId(), genero.getNome(), genero.getSigla());
    }

    public GeneroResponseDTO atualizar(Long id, GeneroRequestDTO dto) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new GeneroNaoEncontradoException());

        genero.setNome(dto.nome());
        genero.setSigla(dto.sigla().toUpperCase());

        Genero atualizado = generoRepository.save(genero);
        return new GeneroResponseDTO(atualizado.getId(), atualizado.getNome(), atualizado.getSigla());
    }

    public void deletar(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new GeneroNaoEncontradoException());

        if (!genero.getLivros().isEmpty())
        // Verificar aí se existem livros vinculados ao gênero usando o repository
        {
            throw new RuntimeException(
                "Não é possível excluir o gênero '" + genero.getNome() +
                "' pois existem livros vinculados a ele."
            );
        }

        generoRepository.deleteById(id);
    }

    public Genero buscarEntidadePorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new GeneroNaoEncontradoException());
    }
}