package br.com.personal.expenses.personalexpenses.security;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;
import br.com.personal.expenses.personalexpenses.dto.User.UserLoginRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.User.UserLoginResponseDTO;
import br.com.personal.expenses.personalexpenses.dto.User.UserResponseDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // essa classe é um filter e vai extender outra para sobrescrever alguns métodos
    // essa classe é utilizada pelo spring para filtrar alguma coisa na autenticação

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    @Autowired
    private ModelMapper mapper;

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
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
                                                                                                     // esse me´todo pode lançar um exception
        try {
            // Pega o que vem no corpo da requisição, mais especificamente a stream de dados
            // e transforma em um modelo DTO do usuário, parecido com model mapper
            UserLoginRequestDTO userLogin = new ObjectMapper().readValue(request.getInputStream(),UserLoginRequestDTO.class);

            // manda o security do spring validar esse usuário através do email e senha
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPasswordUser());
            // veriica se está tudo ok para autenticar
            Authentication auth = authenticationManager.authenticate(authToken);

            return auth;

        }catch(BadCredentialsException e) {
            throw new BadCredentialsException("Usuário ou senha inválidos.");
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("Erro interno: " + e.getMessage());
        }
    }

    // deu certo autenticação?
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,Authentication authResult) throws IOException {
        // toda autenticação filtrada e retornada com sucesso cairá aqui dentro

        // qual é o usuário autenticado
        // getPrincipal() retorna um objeto, por isso é feito um cast
        UserAdmin user = (UserAdmin) authResult.getPrincipal();

        // gerar um token para o usuário com base na classe criada c/ o método que gera o token
        String token = jwtUtil.generateToken(authResult);

        // cria um usuário response com base no usuário autenticado
        UserResponseDTO userResponse = mapper.map(user, UserResponseDTO.class);

        // cria um usuário login response com o token e as infos do usuário response dto
        UserLoginResponseDTO userLoginResponse = new UserLoginResponseDTO();
        userLoginResponse.setTokenAuthorization("Bearer" + token);
        userLoginResponse.setUser(userResponse);

        // altera a resposta
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        // escreve a resposta como json
        response.getWriter().write(new Gson().toJson(userLoginResponse));

    }
}
