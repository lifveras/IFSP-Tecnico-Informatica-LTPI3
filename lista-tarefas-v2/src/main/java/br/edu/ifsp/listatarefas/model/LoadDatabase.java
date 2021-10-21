package br.edu.ifsp.listatarefas.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDataBase(ListaRepository repository){
        return args ->{
           //Salva algumas listas com Tarefas
           Lista lista1 = new Lista("Contas a pagar", "money", "Lista de todas as contas que devo pagar");
           List<Tarefa> tarefas = new ArrayList<>();
           Tarefa tarefa1 = new Tarefa("Pagar conta de luz", Date.valueOf("2021-10-10"));
           tarefa1.setLista(lista1); //necesário adicionar para sincronizar a conexão bidirecional
           tarefas.add(tarefa1); 
           Tarefa tarefa2 = new Tarefa("Pagar conta de gás", Date.valueOf("2021-09-10"));
           tarefa2.setLista(lista1); //necesário adicionar para sincronizar a conexão bidirecional entre Lista e Tarefa
           tarefas.add(tarefa2); 
           lista1.setTarefas(tarefas);

           Lista lista2 = new Lista("Provas do IFSP", "class", "Lista de todas as provas desse semestre");
           tarefas = new ArrayList<>();
           tarefa1 = new Tarefa("Estudar para Física", Date.valueOf("2021-10-10"));
           tarefa1.setLista(lista2); //necesário adicionar para sincronizar a conexão bidirecional
           tarefas.add(tarefa1);
           tarefa2 = new Tarefa("Treinar para a olímpiada de matemática", Date.valueOf("2021-09-10"));
           tarefa1.setLista(lista2); //necesário adicionar para sincronizar a conexão bidirecional
           tarefas.add(tarefa2);
           lista2.setTarefas(tarefas);
           log.info("Preloading " + repository.save(lista1));
           log.info("Preloading " + repository.save(lista2));
        };
    }
}
