package br.edu.ifsp.listatarefas.security.filters;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import br.edu.ifsp.listatarefas.security.credentials.UserCredentials;
import br.edu.ifsp.listatarefas.security.handler.LoginFailureHandler;
import br.edu.ifsp.listatarefas.security.services.JWTTokenAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    JWTTokenAuthenticationService jwtTokenAuthenticationService;

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url), authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // try {
            // Copia o conteúdo de um json para dentro de um objeto
            UserCredentials credenciais = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
            return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(credenciais.getUsername(), credenciais.getPassword()));
        // } catch (JsonParseException ex) { 
        //     response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
        //             "Verifique o JSON enviado no Body. Ele pode estár mal formatado");
        // }
        // return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain, Authentication auth) throws IOException, ServletException {
        System.out.println("Credenciado...");
        jwtTokenAuthenticationService.addAuthentication(response, auth.getName());
    }
}
