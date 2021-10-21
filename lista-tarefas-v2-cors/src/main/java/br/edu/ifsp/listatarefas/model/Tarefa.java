package br.edu.ifsp.listatarefas.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Tarefa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Date prazo;
    @ManyToOne
    @JoinColumn(name = "lista_fk")
    @JsonBackReference
    private Lista lista;
    public Tarefa(){
    }
    public Tarefa(String descricao, Date prazo) {
        this.descricao = descricao;
        this.prazo = prazo;
    }
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
    public Date getPrazo() {
        return prazo;
    }
    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }
    public Lista getLista() {
        return lista;
    }
    public void setLista(Lista lista) {
        this.lista = lista;
    }        
}

