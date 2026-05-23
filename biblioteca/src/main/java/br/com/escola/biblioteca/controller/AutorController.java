package br.com.escola.biblioteca.controller;

import br.com.escola.biblioteca.dto.AutorRequestDTO;
import br.com.escola.biblioteca.dto.AutorResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponseDTO> criar(@RequestBody AutorRequestDTO dto) {
        Autor autor = new Autor(dto.nome(), dto.nacionalidade(), dto.dataNascimento());
        Autor salvo = autorService.salvar(autor);
        return ResponseEntity.ok(new AutorResponseDTO(salvo.getId(), salvo.getNome(), salvo.getNacionalidade(), salvo.getDataNascimento()));
    }

    @GetMapping
    public List<AutorResponseDTO> listar() {
        return autorService.listar().stream()
                .map(a -> new AutorResponseDTO(a.getId(), a.getNome(), a.getNacionalidade(), a.getDataNascimento()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            Autor a = autorService.buscarPorId(id);
            return ResponseEntity.ok(new AutorResponseDTO(a.getId(), a.getNome(), a.getNacionalidade(), a.getDataNascimento()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> atualizar(@PathVariable Long id, @RequestBody AutorRequestDTO dto) {
        try {
            Autor autor = new Autor(dto.nome(), dto.nacionalidade(), dto.dataNascimento());
            Autor atualizado = autorService.atualizar(id, autor);
            return ResponseEntity.ok(new AutorResponseDTO(atualizado.getId(), atualizado.getNome(), atualizado.getNacionalidade(), atualizado.getDataNascimento()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            autorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

