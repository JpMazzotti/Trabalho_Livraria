package br.com.escola.biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autor_id;

    @NotBlank(message = "O nome nao pode estar vazio")
    @Column(name = "nome",nullable = false, length = 30 ) 
    private String nome;

    @NotBlank(message = "A nacionalidade nao pode estar vazia")
    @Column(name = "nacionalidade")
    private String nacionalidade;
    
    @NotNull(message = "A data nao pode estar vazia")
    @Past(message = "A data tem que estar no passado")
    @Column(name = "dataNascimento",nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros = new ArrayList<>();

  
    public Autor() {}

    public Autor(String nome, String nacionalidade, LocalDate dataNascimento) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
    }

   
    public Long getId() {
        return autor_id;
    }

    public void setId(Long id) {
        this.autor_id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        livro.setAutor(this);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
        livro.setAutor(null);
    }


}

