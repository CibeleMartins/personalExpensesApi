package br.com.personal.expenses.personalexpenses.security;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;
import br.com.personal.expenses.personalexpenses.domain.service.UserService;
import br.com.personal.expenses.personalexpenses.dto.User.UserResponseDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    // obter o usuário pelo login
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    // construtor
    public JwtAuthorizationFilter(AuthenticationManager authManager, JwtUtil jwtUtil) {
        super(authManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // capturar o header da requisição pra ver se o token está vindo
        String header = request.getHeader("Authorization"); // bearer: sdbkgksdgsk token

        if (header != null && header.startsWith("Bearer")) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));

            if (auth != null && auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        if (jwtUtil.isValidToken(token)) {
            String userEmail = jwtUtil.getUserName(token);
            UserResponseDTO user = userService.getByEmail(userEmail);

            UserAdmin userAuthenticatedCredentials = mapper.map(user, UserAdmin.class);

            return new UsernamePasswordAuthenticationToken(userAuthenticatedCredentials, null,
                    userAuthenticatedCredentials.getAuthorities());

        }

        return null;
    }

}
