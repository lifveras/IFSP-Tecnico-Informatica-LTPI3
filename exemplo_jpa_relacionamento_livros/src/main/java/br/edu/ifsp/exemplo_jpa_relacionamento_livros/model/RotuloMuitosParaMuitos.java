package br.edu.ifsp.exemplo_jpa_relacionamento_livros.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RotuloMuitosParaMuitos{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    private String descricao;

    /* Configuração de relacionamentos com o JPA*/
    // Referenciado na classe Livro pelo campo "rotulosMuitosParaMuitos"
    @ManyToMany(mappedBy = "rotulosMuitosParaMuitos")
    private List<Livro> livros;

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
    public List<Livro> getLivros() {
        return livros;
    }
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
