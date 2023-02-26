package br.com.personal.expenses.personalexpenses.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    private UserDetailsSecurityServer userDetails;

    // construtor
    public JwtAuthorizationFilter(AuthenticationManager authManager, JwtUtil jwtUtil, UserDetailsSecurityServer userDetails ) {
        super(authManager);
        this.jwtUtil = jwtUtil;
        this.userDetails = userDetails;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // capturar o header da requisição pra ver se o token está vindo
        String header = request.getHeader("Authorization"); // bearer: sdbkgksdgsk token

        if (header != null && header.startsWith("Bearer")) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(6));

            if (auth != null && auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        if (jwtUtil.isValidToken(token)) {
            String userEmail = jwtUtil.getUserName(token);
            
            UserAdmin userModelRepository = (UserAdmin) userDetails.loadUserByUsername(userEmail);

            return new UsernamePasswordAuthenticationToken(userModelRepository, null,userModelRepository.getAuthorities());

        }

        return null;
    }

}
