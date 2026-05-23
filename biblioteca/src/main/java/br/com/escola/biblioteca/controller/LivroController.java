 package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(@RequestBody LivroRequestDTO dto) {
        Livro livro = new Livro(dto.titulo(), dto.isbn(), dto.anoPublicacao(), dto.genero(), null);
        Livro salvo = livroService.salvar(livro, dto.autorId());
        return ResponseEntity.ok(new LivroResponseDTO(
                salvo.getId(), salvo.getTitulo(), salvo.getIsbn(),
                salvo.getAnoPublicacao(), salvo.getGenero(),
                salvo.getAutor().getId(), salvo.getAutor().getNome()));
    }

    @GetMapping
    public List<LivroResponseDTO> listar() {
        return livroService.listar().stream()
                .map(l -> new LivroResponseDTO(l.getId(), l.getTitulo(), l.getIsbn(),
                        l.getAnoPublicacao(), l.getGenero(),
                        l.getAutor().getId(), l.getAutor().getNome()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            Livro l = livroService.buscarPorId(id);
            return ResponseEntity.ok(new LivroResponseDTO(l.getId(), l.getTitulo(), l.getIsbn(),
                    l.getAnoPublicacao(), l.getGenero(),
                    l.getAutor().getId(), l.getAutor().getNome()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable Long id, @RequestBody LivroRequestDTO dto) {
        try {
            Livro livro = new Livro(dto.titulo(), dto.isbn(), dto.anoPublicacao(), dto.genero(), null);
            Livro atualizado = livroService.atualizar(id, livro, dto.autorId());
            return ResponseEntity.ok(new LivroResponseDTO(atualizado.getId(), atualizado.getTitulo(), atualizado.getIsbn(),
                    atualizado.getAnoPublicacao(), atualizado.getGenero(),
                    atualizado.getAutor().getId(), atualizado.getAutor().getNome()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            livroService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

}
