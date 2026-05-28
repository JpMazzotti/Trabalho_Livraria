package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.dto.EditoraRequestDTO;
import br.com.escola.biblioteca.dto.EditoraResponseDTO;
import br.com.escola.biblioteca.service.EditoraService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
@SecurityRequirement(name = "bearerAuth")
public class EditoraController {

    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EditoraResponseDTO>> listarTodas() {
        return ResponseEntity.ok(editoraService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(editoraService.buscarPorId(id));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<EditoraResponseDTO> criar(@Valid @RequestBody EditoraRequestDTO dto) {
        return ResponseEntity.ok(editoraService.cadastrar(dto));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EditoraResponseDTO> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody EditoraRequestDTO dto) {
        return ResponseEntity.ok(editoraService.atualizar(id, dto));
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        editoraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
