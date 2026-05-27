package br.com.escola.biblioteca.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record AutorRequestDTO(


    @NotBlank(message = "O nome nao pode estar vazio")
    String nome,

    @NotBlank(message = "A nacionalidade nao pode estar vazia")
    String nacionalidade,

    @NotNull(message = "A data nao pode estar vazia")
    @Past(message = "A data tem que estar no passado")
    LocalDate dataNascimento
) {}