package br.com.personal.expenses.personalexpenses.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity //diz que é uma config de segurança web
public class WebSecurityConfig {
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationConfiguration authConfiguration;

    @Autowired
    private UserDetailsSecurityServer userDetails;

    @Bean //p poder usar em qualquer lugar
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        // quando for solicitado uma config de authnetication manager com base no authentication configuration será retornado
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // desabilita algumas coisas e para cada requisição toma uma ação
        http.headers().frameOptions()
        .disable().and().cors().and()
        .csrf().disable().authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.POST, "/api/users").permitAll().anyRequest().authenticated()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // qualquer outra requisição que não seja POST vai precisar estar autenticado para conseguir fazer
        
        http.addFilter(new JwtAuthenticationFilter(authenticationManager(authConfiguration), jwtUtil));
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(authConfiguration), jwtUtil, userDetails));

        return http.build();
    }
}
