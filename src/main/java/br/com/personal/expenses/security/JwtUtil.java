package br.com.personal.expenses.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

// para utilizar a classe de maneira útil em toda aplicação
@Component
public class JwtUtil {

    // criptografar e descriptografar os tokens
    @Value("${auth.jwt.secret}")
    private String jwtSecret;

    // data de expiração em milisegundos
    @Value("${auth.jwt-expiration-milliseg}")
    private Long jwtExpirationMilliseg;
    // essas propriedades não vão ser criadas de maneira dinâmica, 
    // virão do application.properties, por isso a anotação '@Value()'

    // um método que devolve uma string e gera um token
    public String generateToken(Authentication authentication) {
        // quando o usuário já estiver com login e senha corretos
        // vai recber um objeto com as informações de autenticação

        // data de expiração do token
        // data atual mais um dia
        Date dateExpiration = new Date(new Date().getTime() + jwtExpirationMilliseg);
        // método get() retorna os milisegundos da data atual 
        // pega a data atual e soma mais um dia em milisegundos

        
    }
    

}
