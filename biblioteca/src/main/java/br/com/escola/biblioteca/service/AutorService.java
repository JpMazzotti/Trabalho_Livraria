package br.com.escola.biblioteca.service;

import br.com.escola.biblioteca.dto.AutorRequestDTO;
import br.com.escola.biblioteca.dto.AutorResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.exception.AutorInesistenteException;
import br.com.escola.biblioteca.exception.ExclusaoNaoPermitidaException;
import br.com.escola.biblioteca.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorResponseDTO criar(AutorRequestDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        Autor salvo = autorRepository.save(autor);
        return AutorResponseDTO.fromEntity(salvo);
    }

    public List<AutorResponseDTO> listarTodos() {
        return autorRepository.findAll()
                .stream()
                .map(AutorResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public AutorResponseDTO buscarPorId(Long autor_id) {
        Autor autor = autorRepository.findById(autor_id)
                .orElseThrow(() -> new AutorInesistenteException());
        return AutorResponseDTO.fromEntity(autor);
    }

    public AutorResponseDTO atualizar(Long autor_id, AutorRequestDTO dto) {
        Autor autor = autorRepository.findById(autor_id)
                .orElseThrow(() -> new AutorInesistenteException());

        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());

        Autor atualizado = autorRepository.save(autor);
        return AutorResponseDTO.fromEntity(atualizado);
    }

    public void deletar(Long autor_id) {
        
        Autor autor = autorRepository.findById(autor_id)
                .orElseThrow(() -> new AutorInesistenteException());

        if (!autor.getLivros().isEmpty()) {
            throw new ExclusaoNaoPermitidaException("Não é possível excluir o autor, pois existem livros vinculados a ele.");
        }
        autorRepository.delete(autor);
    }

    public Autor buscarEntidadePorId(Long autor_id) {
        return autorRepository.findById(autor_id)
               .orElseThrow(() -> new AutorInesistenteException());
    }
}

