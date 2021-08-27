package br.edu.ifsp.listacomprasv1.model;

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
            //Aqui você vai colocar seu código de inserção de dados iniciais no banco
            Lista lista1 = new Lista("Contas a pagar", "money", "Lista de todas as contas que devo pagar!");
            Lista lista2 = new Lista("Provas do IFSP", "aula", "Lista de todas as provas do semetre!");

            log.info("Inserindo: " + repository.save(lista1));
            log.info("Inserindo: " + repository.save(lista2));
        };
    }
}
