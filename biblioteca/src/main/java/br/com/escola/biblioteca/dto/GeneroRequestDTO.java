package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GeneroRequestDTO(
        @NotBlank(message = "O campo nome é obrigatório.")
        String nome,

        @NotBlank(message = "A sigla do genero é obrigatória.")
        @Size(max = 3, message = "A sigla do genero deve conter no máximo 3 caracteres.")
        String sigla
) {}