package br.edu.ifsp.listatarefas.security.services;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenAuthenticationService {
    // EXPIRATION_TIME = 10 dias em millisegundos
	static final long EXPIRATION_TIME = 860_000_000;
	static final String SECRET = "MyS3cret"; // [ATENÇÃO] Você deve criar um segredo próprio para a sua aplicação.
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    // Cria uma autenticação JWT
    public static void addAuthentication(HttpServletResponse response, String username) throws IOException{
        // Aqui o JWT é criptografado para retornar ao cliente.
        String JWT = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    // .setExpiration(null)
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();
        // Adiciona o Token no Header da Resposta ...
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // .. e também no Body como JSON.
        response.getWriter().write(
            "{\"data\": { \"token\":\"" + JWT + "\"}}"
        );
    }

    // Verifica se o JWT enviado é válido e autentica o usuário
    public Authentication getAuthentication(HttpServletRequest request){

        String token = request.getHeader(HEADER_STRING);

        if(token != null){
            // Aqui o JWT é descriptografado para ser pesquisado no banco de usuário, por exemplo.
            // faz parse do token
            String user = Jwts.parser()
                         .setSigningKey(SECRET)
                         .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                         .getBody()
                         .getSubject();
            
            /* Recupera do banco de dados o usuário recebido via HTTP.
               e transforma para UserDetails, um formato que o Spring Security 
               entende para representar um usuário de login*/
            /**USE A BUSCA POR UM USUÁRIO NO SEU REPOSITÓRIO AQUI***/   
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user);

            if(user != null){
                // Cria o usuário autenticado para validação pelo Spring
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
        }
        return null;
    }
}
