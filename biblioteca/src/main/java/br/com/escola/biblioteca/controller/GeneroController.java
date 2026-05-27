package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.dto.GeneroRequestDTO;
import br.com.escola.biblioteca.dto.GeneroResponseDTO;
import br.com.escola.biblioteca.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
@Tag(name = "Gêneros", description = "Endpoints para gerenciamento de gêneros literários")
@SecurityRequirement(name = "bearerAuth")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todos os gêneros")
    public ResponseEntity<List<GeneroResponseDTO>> listarTodos() {
        return ResponseEntity.ok(generoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar gênero por ID")
    public ResponseEntity<GeneroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.buscarPorId(id));
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Cadastrar novo gênero (ex: Romance = ROM)")
    public ResponseEntity<GeneroResponseDTO> criar(@Valid @RequestBody GeneroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.criar(dto));
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar gênero por ID")
    public ResponseEntity<GeneroResponseDTO> atualizar(
            @PathVariable Long id, @Valid @RequestBody GeneroRequestDTO dto) {
        return ResponseEntity.ok(generoService.atualizar(id, dto));
    }

    @DeleteMapping("/remover/{id}")
    @Operation(summary = "Deletar gênero por ID (bloqueado se houver livros vinculados)")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        generoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
