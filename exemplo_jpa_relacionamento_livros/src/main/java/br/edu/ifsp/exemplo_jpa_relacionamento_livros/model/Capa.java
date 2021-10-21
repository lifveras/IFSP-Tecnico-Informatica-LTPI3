package br.edu.ifsp.exemplo_jpa_relacionamento_livros.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Capa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    
    public Long id;
    public Character tipo;
    
    /* Configuração de relacionamentos com o JPA*/
    // Referenciado na classe Livro pelo campo "capa"
    @OneToOne(mappedBy = "capa") 
    public Livro livro;

    public Capa(Long id, Character tipo) {
        this.id = id;
        this.tipo = tipo;
    }    
}
