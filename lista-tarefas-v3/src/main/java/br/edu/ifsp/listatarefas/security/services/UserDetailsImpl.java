package br.edu.ifsp.listatarefas.security.services;

import java.util.Collection;
import java.util.Collections;

import br.edu.ifsp.listatarefas.model.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
		this.usuario = usuario;
	}

    public static UserDetailsImpl build(Usuario usuario){
        return new UserDetailsImpl(usuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    /* TODOS OS MÉTODOS A SEGUIR DEVEM RETORNAR TRUE */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}