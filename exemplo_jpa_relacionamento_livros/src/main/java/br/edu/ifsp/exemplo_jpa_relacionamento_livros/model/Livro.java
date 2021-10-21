package br.edu.ifsp.exemplo_jpa_relacionamento_livros.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Float preco;
    public String titulo;
    public Integer numpaginas;
    public String descricao;

    /* Configuração de relacionamentos com o JPA*/
    @OneToOne
    @JoinColumn(name = "fk_capa")
    public Capa capa;

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rotulo> rotulos;

    @ManyToMany
    @JoinTable(name = "associacao_livro_rotulo",
               joinColumns = @JoinColumn(name = "fk_livro"),
               inverseJoinColumns = @JoinColumn(name = "fk_rotulo"))               
    private List<RotuloMuitosParaMuitos> rotulosMuitosParaMuitos;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Float getPreco() {
        return preco;
    }
    public void setPreco(Float preco) {
        this.preco = preco;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getNumpaginas() {
        return numpaginas;
    }
    public void setNumpaginas(Integer numpaginas) {
        this.numpaginas = numpaginas;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Capa getCapa() {
        return capa;
    }
    public void setCapa(Capa capa) {
        this.capa = capa;
    }
    public List<Rotulo> getRotulos() {
        return rotulos;
    }
    public void setRotulos(List<Rotulo> rotulos) {
        this.rotulos = rotulos;
    }
    public List<RotuloMuitosParaMuitos> getRotulosMuitosParaMuitos() {
        return rotulosMuitosParaMuitos;
    }
    public void setRotulosMuitosParaMuitos(List<RotuloMuitosParaMuitos> rotulosMuitosParaMuitos) {
        this.rotulosMuitosParaMuitos = rotulosMuitosParaMuitos;
    }    
}
