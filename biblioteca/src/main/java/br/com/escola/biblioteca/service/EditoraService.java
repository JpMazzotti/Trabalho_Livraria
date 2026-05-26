@Transactional
public void deletar(Long id) {
    Editora editora = EditoraRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Editora não encontrada."));

    if (!editora.getLivros().isEmpty()) {
        throw new RuntimeException("Não é possível excluir a editora pois existem livros vinculados a ela.");
    }

    EditoraRepository.delete(editora);
}