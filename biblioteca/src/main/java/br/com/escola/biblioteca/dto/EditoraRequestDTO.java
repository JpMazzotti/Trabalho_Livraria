package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record EditoraRequestDTO(
        @NotBlank(message = "O campo 'nome' é obrigatório.")
        String nome,

        @NotBlank(message = "O campo 'cnpj' é obrigatório.")
        @CNPJ(message = "O campo 'cnpj' deve ser um CNPJ válido.")
        String cnpj,

        @NotBlank(message = "O campo 'estado' é obrigatório.")
        @Size(min = 2, max = 2, message = "O campo 'estado' deve conter 2 caracteres(Ex: RJ, MG).")
        String estado
) {}