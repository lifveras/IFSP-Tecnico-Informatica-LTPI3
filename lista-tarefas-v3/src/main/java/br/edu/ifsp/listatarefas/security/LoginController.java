package br.edu.ifsp.listatarefas.security;

import br.edu.ifsp.listatarefas.repository.UsuarioRepository;
import br.edu.ifsp.listatarefas.model.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginController {
    
    Logger logger = LoggerFactory.getLogger(LoginController.class);

     //[ATENÇÃO] Trocar para o repositório do seu usuário
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    //[ATENÇÃO] Trocar para a classe de seu usuário ou criar uma tabela nova para tal. 
    @PostMapping(path = "/login/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createNewUser(@RequestBody Usuario user){
        //Criptografa a senha do usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usuarioRepository.save(user);
        return "Usuário cadastrado com sucesso";
    }

    @GetMapping("/home")
    public String getHome(){
        return " {'categoria:' 'house-door-fill'," +
                  "'titulo': 'Compras da casa'," +
                  "'descricao': 'ok'," +
                  "'dataCriacao': '29/09/2021'," +
                  "'id': 1}";
    }

    // @GetMapping("/usuarios")
    // @ResponseBody
    // public String getUsers(){
    //     return "{\"users\":[{\"name\":\"Lucas\", \"country\":\"Brazil\"}," +
    //     "{\"name\":\"Jackie\",\"country\":\"China\"}]}";
    // }
}
