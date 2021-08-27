package br.edu.ifsp.listacomprasv1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.listacomprasv1.model.Lista;
import br.edu.ifsp.listacomprasv1.model.ListaRepository;

@RestController
public class ListaController {
    
    @Autowired
    ListaRepository listaRepository;

    @GetMapping("/listas")
    public List<Lista> getListas(){
        return (List<Lista>) listaRepository.findAll();
    }
    
    @PostMapping("/listas/criar")
    public Lista postLista(@RequestBody Lista lista){
        return listaRepository.save(lista);
    }
}
