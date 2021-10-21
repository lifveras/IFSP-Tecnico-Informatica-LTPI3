package br.edu.ifsp.listatarefas.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifsp.listatarefas.security.handler.LoginFailureHandler;
import br.edu.ifsp.listatarefas.security.services.JWTTokenAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean{

    @Autowired 
    LoginFailureHandler loginFailureHandler;
    @Autowired
    JWTTokenAuthenticationService jwtTokenAuthenticationService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try{
            // A autenticação criada no JWTLoginFilter é recuperada aqui.
            // Para simplificar, a requisição será 
            Authentication authentication = jwtTokenAuthenticationService
                .getAuthentication((HttpServletRequest) request);
            
            // Habilita acesso no Spring para o usuário autenticado
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Chama o próximo filtro, se houver
            filterChain.doFilter(request, response);
        } catch(UsernameNotFoundException ex){
            loginFailureHandler.onAuthenticationFailure(
                (HttpServletRequest)request, 
                (HttpServletResponse)response, ex);
        }
    }
}
