package br.edu.ifsp.listatarefas.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.listatarefas.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
     // Você não vai precisar implementar este método em nenhum lugar.
    // O Spring Data JPA automaticamente vai criar a funcionalidade para você.
    // padrão: findBy<Nome do campo>(<Tipo do campo> valor de busca) 
    public Usuario findByUsername(String username);
}
