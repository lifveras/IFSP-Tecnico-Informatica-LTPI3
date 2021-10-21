package br.edu.ifsp.listatarefas.security;

import br.edu.ifsp.listatarefas.security.filters.JWTAuthenticationFilter;
import br.edu.ifsp.listatarefas.security.filters.JWTLoginFilter;
import br.edu.ifsp.listatarefas.security.handler.AuthEntryPointJwt;
import br.edu.ifsp.listatarefas.security.handler.LoginFailureHandler;
import br.edu.ifsp.listatarefas.security.services.UserDetailsServiceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@EnableWebSecurity //Configura no Spring essa classe à segurança global da API
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    /** Explica bem porque algumas vezes o Autowired resulta em objeto nulo: https://www.moreofless.co.uk/spring-mvc-java-autowired-component-null-repository-service/*/
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
	private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    // Por ser anotado com Bean, o que for retornado por esse método, 
    // estará disponível em toda a aplicação Spring
    @Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    // Método que configura a segurança via HTTP
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //Roteiro de configuração:
            // 1. CORS
            // 2. Definir como tratar acesso não autorizado
            // 3. Criar forma de sessão (como os dados do usuário serão mantidos aqui no servidor)
            // 4. Configurar os paths que estão abertos para todo mundo
            // 5. Definir que qualquer outro path deve exigir autenticação
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/login/create").permitAll()
                .antMatchers(HttpMethod.POST, "/login/auth").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout().invalidateHttpSession(true).logoutUrl("/logout");
        // Configura um filtro que executa uma vez antes de cada requisição
        // http
        //     .addFilterBefore(new AuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // Direção: Client -> Server. Executado quando um Client vai fazer login na nossa aplicação.
        // Ex: Post /login/auth -> Esse filtro então irá receber o ususario e senha e verificar se ele pode acessar a aplicação
        http
            .addFilterBefore(new JWTLoginFilter("/login/auth", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    
         // Direção: Server -> Client. Executado para devolver uma autenticação para o Client, verificando se ele já está logado.
         // É aplicado sob todas as outras rotas, ou seja, é o que impede o acesso a um path caso você não esteja logado.
         // Ex: Get /usuarios -> Erro se não logado, Ok se logado.
         http
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    LoginFailureHandler authenticationFailureHandler(){
        return new LoginFailureHandler();
    }

    // Ver explicação em https://www.baeldung.com/spring-security-cors-preflight
    // @Bean
    // CorsConfigurationSource corsConfigurationSource() {
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.setAllowedOrigins(Arrays.asList("*"));
    //     // configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT", "OPTION"));
    //     configuration.setAllowedMethods(Arrays.asList("*"));
    //     configuration.setAllowedHeaders(Arrays.asList("*"));
    //     configuration.setAllowCredentials(false);
    //     source.registerCorsConfiguration("/**", configuration);
    //     return source;
    // }
}
