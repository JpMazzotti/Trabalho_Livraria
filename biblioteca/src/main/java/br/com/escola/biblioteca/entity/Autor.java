package br.com.escola.biblioteca.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor" )
    private Long id;

    @Column(name = "nome",nullable = false, length = 30 ) 
    private String nome;

    @Column(name = "nacionalidade" , nullable = false)
    private String nacionalidade;
    
    @Column(name = "dataNascimento",nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros = new ArrayList<>();

  
    public Autor() {}

    public Autor(String nome, String nacionalidade, LocalDate dataNascimento) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

