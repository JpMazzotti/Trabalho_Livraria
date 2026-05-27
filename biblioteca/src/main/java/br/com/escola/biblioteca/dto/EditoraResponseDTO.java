package br.com.escola.biblioteca.dto;
import br.com.escola.biblioteca.entity.Editora;

public record EditoraResponseDTO(
        Long id,
        String nome,
        String cnpj,
        String estado
) 
 {
    public static EditoraResponseDTO fromEntity(Editora editora) {
        return new EditoraResponseDTO(
            editora.getId(),
            editora.getNome(),
            editora.getCnpj(),
            editora.getEstado()
        );
    }
}
