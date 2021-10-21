package br.edu.ifsp.listatarefas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.listatarefas.model.Tarefa;

public interface TarefaRepository extends CrudRepository<Tarefa, Long>{
    // Você não vai precisar implementar este método em nenhum lugar.
    // O Spring Data JPA automaticamente vai criar a funcionalidade para você.
    // padrão: findby<Nome do campo>(<Tipo do campo> valor de busca) 
    List<Tarefa> findByPrazo(Date prazo);
}

