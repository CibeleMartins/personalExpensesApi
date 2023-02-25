package br.com.personal.expenses.personalexpenses.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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

        // saber quem é o usuário que está gerando o token?
        UserAdmin userAdmin = (UserAdmin) authentication.getPrincipal();
        // getPrincipal() devolve um objeto, por isso um cast
        // para o que vier no objeto ser convertido em um usuário
        // assim, pega o usuário autenticado

        // cria o token

        try {

            // gera a chave para o padrão de criptografia do token
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));
            // esse método recebe um array de bytes e transforma numa chave

            // jwt tem um builder que recebe algumas propriedades
            return Jwts.builder().setSubject(userAdmin.getUsername()).setIssuedAt(new Date())
                    .setExpiration(dateExpiration).signWith(secretKey).compact();
            // setSubject() recebe o dono do token, mas ele espera receber um string
            // por isso, foi passado um valor único que identifique o usuário
            // setIssuedAt() recebe a data que o token é gerado
            // setExpiration() recebe a data de expiração do token
            // compact() gera o token, pega tudo e compacta gerando uma string
            // mas é necessário definir o padrão de criptografia que será usado na geração
            // do token
            // para isso, o signWith()

        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "";
        }

    }

    // além de gerar o token, também é preciso saber o que tem no token
    // se ele é válido ou não, para isso tem este método
    private Claims getClaims(String token) {
        // recebe como parametro o token gerado
    
        try {
            // é necessário uma chave p descriptografar o token
            Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes("UTF-8"));

            // e vai pegar as claims do token
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            // estes métodos fazem a descriptografia 
            // parserBuilder() cria um builder com essa senha setSigningKey(secretKey)
            // manda construir build()
            // pega as claims do token parseClaimsJws(token)
            // as informações dentro do token

            return claims;

        } catch (Exception e) {

            // System.out.println(e.getMessage());
            e.printStackTrace();

            // se não conseguir as claims retorna null
            return null;
        }
    }


    // pega o email do usuário dentro do token
    public String getUserName(String token) {
        
        Claims claims = getClaims(token);

        if(claims == null) {
            return null;
        }

        // pegar quem é o dono do usuário
        return claims.getSubject();
    }

    // método para validar o token
    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);

        if(claims == null) {
            return false;
        }

        // pega o dono do token
        String email = claims.getSubject();
        // pega a data de expiração
        Date dateExpiration = claims.getExpiration();

        // a data está espirada?
        // pega a data atual do sistema e a data de agora
        Date now = new Date(System.currentTimeMillis());

        // se o email não for nulo e a data de agora for anterior a data de expiração
        if(email != null && now.before(dateExpiration)) {
            return true;
        }

        return false;

    }
}
