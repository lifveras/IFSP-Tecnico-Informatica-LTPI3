package br.edu.ifsp.exemplo_jpa_relacionamento_livros.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rotulo {   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String descricao;

    /* Configuração de relacionamentos com o JPA*/
    // Referenciado na classe Livro pelo campo "rotulos"
    @ManyToOne
    private Livro livro;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
