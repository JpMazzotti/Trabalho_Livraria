package br.com.escola.biblioteca.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long id;

    @NotBlank(message = "O título não pode estar em branco")
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "O código ISBN não pode estar em branco")
    @Column(name = "isbn", nullable = false, length = 20, unique = true)
    private String isbn;

    @Past(message = "A data de publicação precisa estar no passado")
    @NotNull(message = "O ano de publicação deve existir")
    @Column(name = "ano_publicacao", nullable = false)
    private LocalDate anoPublicacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_editora", nullable = false)
    private Editora editora;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    public Livro() {
    }

    public Livro(String titulo, String isbn, LocalDate anoPublicacao, Autor autor, Editora editora, Genero genero) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.editora = editora;
        this.genero = genero;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(LocalDate anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
