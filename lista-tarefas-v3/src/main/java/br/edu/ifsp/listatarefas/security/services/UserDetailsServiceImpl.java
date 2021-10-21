package br.edu.ifsp.listatarefas.security.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifsp.listatarefas.repository.UsuarioRepository;
import br.edu.ifsp.listatarefas.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{    
    
    //[ATENÇÃO] No código de exemplo, importar o repositório da sua tabela de usuário
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if(usuario == null)
            throw new UsernameNotFoundException(username);
        return UserDetailsImpl.build(usuario);        
    }
}
