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
    private Long id;

    @NotBlank(message = "O titulo nao pode estar em branco")
    @Column(name = "titulo",nullable = false)
    private String titulo;


    @NotBlank(message = "O codigo do livro nao pode estar em branco")
    @Column(name = "isbn",nullable = false)
    private String isbn;
    
    @Past(message = "A data de publicação precisa estar no passado")
    @NotNull(message = "O Ano publicado devde exitir")
    @Column(name = "anoPublicacao",nullable = false)
    private LocalDate anoPublicacao;

    
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false) 
    private Autor autor;

    public Livro() {}

    public Livro(String titulo, String isbn, LocalDate anoPublicacao, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public LocalDate getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(LocalDate anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
}