package br.edu.ifsp.listatarefas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.listatarefas.model.Lista;
import br.edu.ifsp.listatarefas.model.Tarefa;
import br.edu.ifsp.listatarefas.repository.ListaRepository;
import br.edu.ifsp.listatarefas.repository.TarefaRepository;

@CrossOrigin
@RestController
class ListaController {
    
    private static final Logger log = LoggerFactory.getLogger(ListaController.class);

    /*Basta definir um campo do tipo do repositório com a 
      annotation @Autowired que o Spring irá criar um objeto 
      pra gente (isso se chama Injeção de Dependência).*/
      @Autowired 
      ListaRepository listaRepository;
      @Autowired
      TarefaRepository tarefaRepository;
  
      @GetMapping("/listas")
      public List<Lista> getListas(){
          return (List<Lista>) listaRepository.findAll();
      }
      
      @GetMapping("/listas/{id}")
      @ResponseBody
      public Lista getListaById(@PathVariable("id") Lista lista){
          return lista;
      }
  
      @PostMapping("/listas/criar")
      public Lista postLista(@RequestBody Lista lista){
          listaRepository.save(lista);
          if(listaRepository.findById(lista.getId()).isPresent())
              return lista;
          return null;
      }
  
      @PostMapping("/listas/{id}/criar_tarefa")
      public Tarefa postTarefaToLista(@PathVariable Long id, @RequestBody Tarefa tarefa){
          if(listaRepository.findById(id).isPresent()){
              Lista lista = listaRepository.findById(id).get();
              log.debug(lista.getCategoria());
              tarefa.setLista(lista);
              tarefa = tarefaRepository.save(tarefa);
              return tarefa;
          }
          return null;
      }
  
      @DeleteMapping("/listas/deletar_tarefa/{id}")
      public Tarefa deleteTarefa(@PathVariable Long id){
          if(tarefaRepository.findById(id).isPresent()){
              Tarefa tarefa = tarefaRepository.findById(id).get();
              tarefaRepository.delete(tarefa);
              return tarefa;
          }
          return null;
      }

      @PutMapping("/listas/alterar_tarefa/{id}")
      public Tarefa putTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa){
          if(tarefaRepository.findById(id).isPresent()){
              Tarefa tarefaBanco = tarefaRepository.findById(id).get();
              tarefaBanco.setDescricao(tarefa.getDescricao());
              tarefaBanco.setPrazo(tarefa.getPrazo());
              tarefaRepository.save(tarefaBanco);
              return tarefa;
          }
          return null;
      }

    
}