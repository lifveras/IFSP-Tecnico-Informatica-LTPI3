package br.edu.ifsp.listatarefas.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
    @Override
    public void commence(HttpServletRequest request, 
                        HttpServletResponse response, 
                        AuthenticationException exception) throws IOException, ServletException {
        logger.error("Erro de autenticação: {}", exception.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Você não tem acesso! Usuário ou senha incorretos!");
    }
}
