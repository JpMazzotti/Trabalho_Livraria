package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Editora>> listarTodas() {
        List<Editora> lista = editoraService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editora> buscarPorId(@PathVariable Long id) {
        Editora editora = editoraService.buscarPorId(id);
        return ResponseEntity.ok(editora);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Editora> criar(@Valid @RequestBody Editora novaEditora) {
        Editora editora = editoraService.cadastrar(novaEditora);
        return ResponseEntity.ok(editora);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Editora> atualizar(@PathVariable Long id, @Valid @RequestBody Editora editoraAtualizada) {
        Editora editora = editoraService.atualizar(id, editoraAtualizada);
        return ResponseEntity.ok(editora);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        editoraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}