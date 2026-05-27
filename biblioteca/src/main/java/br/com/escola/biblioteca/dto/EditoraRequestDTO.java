package br.com.escola.biblioteca.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EditoraRequestDTO(
        @NotBlank(message = "O nome da editora é obrigatório") String nome,

        @NotBlank(message = "O CNPJ é obrigatório")
        @CNPJ(message = "O CNPJ informado é inválido")
        String cnpj,

        @NotBlank(message = "O estado é obrigatório")
        @Size(min = 2, max = 3, message = "O estado deve ter 2 ou 3 caracteres")
                String estado) {}