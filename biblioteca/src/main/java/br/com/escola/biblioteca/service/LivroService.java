package br.com.escola.biblioteca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.escola.biblioteca.config.MailConfig;
import br.com.escola.biblioteca.dto.LivroRequestDTO;
import br.com.escola.biblioteca.dto.LivroResponseDTO;
import br.com.escola.biblioteca.entity.Autor;
import br.com.escola.biblioteca.entity.Editora;
import br.com.escola.biblioteca.entity.Genero;
import br.com.escola.biblioteca.entity.Livro;
import br.com.escola.biblioteca.exception.AutorInesistenteException;
import br.com.escola.biblioteca.exception.EditoraNaoEncontradaException;
import br.com.escola.biblioteca.exception.LivroDependenciasAusentesException;
import br.com.escola.biblioteca.exception.LivroNaoEncontradoException;
import br.com.escola.biblioteca.exception.GeneroNaoEncontradoException;
import br.com.escola.biblioteca.repository.AutorRepository;
import br.com.escola.biblioteca.repository.EditoraRepository;
import br.com.escola.biblioteca.repository.GeneroRepository;
import br.com.escola.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private MailConfig emailConfig;

    public LivroResponseDTO salvandoLivro(LivroRequestDTO dto) {

        if (dto.autorId() == null || dto.editoraId() == null || dto.generoId() == null) {
            throw new LivroDependenciasAusentesException();
        }

        Autor autor = autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new AutorInesistenteException());

        Editora editora = editoraRepository.findById(dto.editoraId())
                .orElseThrow(() -> new EditoraNaoEncontradaException());

        Genero genero = generoRepository.findById(dto.generoId())
                .orElseThrow(() -> new GeneroNaoEncontradoException());

        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setIsbn(dto.isbn());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setAutor(autor);
        livro.setEditora(editora);
        livro.setGenero(genero);

        Livro livroSalvo = livroRepository.save(livro);

      
    emailConfig.enviarEmail(
        "jricken@faeterj-petropolis.edu.br",
        "Novo livro cadastrado",
        """
        <html>
          <body style="font-family: Arial, sans-serif; background-color:#c8e6c9; padding:20px; color:#333;">
            <h2 style="text-align:center;">📚 Novo Livro Cadastrado</h2>
            <p>Um novo livro foi adicionado ao sistema da biblioteca:</p>
            <p><b>Título:</b> """ + livroSalvo.getTitulo() + """
            </p>
            <p><b>ISBN:</b> """ + livroSalvo.getIsbn() + """
            </p>
        <p><b>Ano de Publicação:</b> """ + livroSalvo.getAnoPublicacao() + """
        </p>
            <p style="text-align:center; color:#555;">Obrigado por utilizar nosso sistema de gerenciamento de livros.</p>
          </body>
        </html>
        """
    );


        return LivroResponseDTO.fromEntity(livroSalvo);
    }

    public List<LivroResponseDTO> buscarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        return LivroResponseDTO.fromEntity(livro);
    }

    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));

        Autor autor = autorRepository.findById(dto.autorId())
                .orElseThrow(() -> new AutorInesistenteException());

        Editora editora = editoraRepository.findById(dto.editoraId())
                .orElseThrow(() -> new EditoraNaoEncontradaException());

        Genero genero = generoRepository.findById(dto.generoId())
                .orElseThrow(() -> new GeneroNaoEncontradoException());

        livroExistente.setTitulo(dto.titulo());
        livroExistente.setIsbn(dto.isbn());
        livroExistente.setAnoPublicacao(dto.anoPublicacao());
        livroExistente.setAutor(autor);
        livroExistente.setEditora(editora);
        livroExistente.setGenero(genero);

        Livro livroAtualizado = livroRepository.save(livroExistente);

        emailConfig.enviarEmail(
        "jricken@faeterj-petropolis.edu.br",
        "Livro atualizado",
        """
        <html>
          <body style="font-family: Arial, sans-serif; background-color:#90caf9; padding:20px; color:#333;">
            <h2 style="text-align:center;">✏️ Livro Atualizado</h2>
            <p>As informações do livro foram atualizadas com sucesso:</p>
            <p><b>Título:</b> """ + livroAtualizado.getTitulo() + """
            </p>
            <p><b>ISBN:</b> """ + livroAtualizado.getIsbn() + """
            </p>
        <p><b>Ano de Publicação:</b> """ + livroAtualizado.getAnoPublicacao() + """
        </p>
            <p style="text-align:center; color:#555;">Obrigado por manter o sistema sempre atualizado.</p>
          </body>
        </html>
        """
    );

        return LivroResponseDTO.fromEntity(livroAtualizado);
    }

   public void deletar(Long id) {
    Livro livro = livroRepository.findById(id)
            .orElseThrow(() -> new LivroNaoEncontradoException(id));

    livroRepository.delete(livro);

  emailConfig.enviarEmail(
        "jricken@faeterj-petropolis.edu.br",
        "Livro excluído",
        """
        <html>
          <body style="font-family: Arial, sans-serif; background-color:#e57373; padding:20px; color:#333;">
            <h2 style="text-align:center;">🗑️ Livro Removido</h2>
            <p>O livro foi removido do sistema da biblioteca:</p>
            <p><b>Título:</b> """ + livro.getTitulo() + """
            </p>
            <p><b>ISBN:</b> """ + livro.getIsbn() + """
            </p>
        <p><b>Ano de Publicação:</b> """ + livro.getAnoPublicacao() + """
        </p>
            <p style="text-align:center; color:#555;">Remoção concluída com sucesso.</p>
          </body>
        </html>
        """
    );
}
}

