package br.com.personal.expenses.personalexpenses.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // essa classe é um filter e vai extender outra para sobrescrever alguns métodos
    // essa classe é utilizada pelo spring para filtrar alguma coisa na autenticação

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    // esses atributos vão ser recebidos no construtor dessa classe
    public  JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        // chama os métodos da classe pai
        super();
        // constrói a propriedades de acordo com os parâmetros recebidos
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

        // esse método recebe uma url que fará com que só chegue 
        // nesse filtro de autenticação criado, caso acesse o usuário acesse essa url
        setFilterProcessesUrl("/api/auth");
    }

    // existe um método da classe extendida 
    // que é utilizado para receber requisição
    // toda vez que uma requisição chegar nesse filtro
    // chegará dentro desse método
    @Override
    public Authentication atteptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
                                                                                                     // esse me´todo pode lançar um exception

        try {
            
        }catch(Exception e) {

        }
    }
}
