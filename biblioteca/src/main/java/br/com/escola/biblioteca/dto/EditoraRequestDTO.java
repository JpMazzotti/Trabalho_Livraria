package br.com.escola.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EditoraRequestDTO(
                @NotBlank(message = "O nome da editora é obrigatório") 
                String nome,

                @NotBlank(message = "O CNPJ é obrigatório") 
                @Pattern(regexp = "\\d{14}", message = "O CNPJ deve ter exatamente 14 dígitos numéricos") 
                String cnpj,
                
                @NotBlank(message = "O estado é obrigatório")
                @Size(min = 2, max = 3, message = "O estado deve ter 2 ou 3 caracteres")
                String estado) {
}