package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@SecurityRequirement(name = "bearerAuth")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LivroResponseDTO>> listarTodos() {
        return ResponseEntity.ok(livroService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<LivroResponseDTO> criar(@RequestBody @Valid LivroRequestDTO dto) {
        LivroResponseDTO response = livroService.salvandoLivro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid LivroRequestDTO dto) {
        LivroResponseDTO response = livroService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
