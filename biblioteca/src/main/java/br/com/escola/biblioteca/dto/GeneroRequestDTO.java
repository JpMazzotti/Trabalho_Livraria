package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GeneroRequestDTO(
        @NotBlank(message = "O nome do gênero é obrigatório")
        String nome,
        
        @NotBlank(message = "A sigla é obrigatória")
        @Size(min = 3, max = 3, message = "A sigla deve ter exatamente 3 caracteres")
        String sigla
) {}