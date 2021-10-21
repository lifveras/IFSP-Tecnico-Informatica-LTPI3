package br.edu.ifsp.listatarefas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String categoria;
    private String descricao;
    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Tarefa> tarefas;
    public Lista() {
    }
    public Lista(String titulo, String categoria, String descricao) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.descricao = descricao;
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
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
    public void addTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }
}
