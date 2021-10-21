package br.edu.ifsp.listatarefas.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.listatarefas.model.Lista;

public interface ListaRepository extends CrudRepository<Lista, Long>{
    
}
