package br.com.personal.expenses.personalexpenses.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;
import br.com.personal.expenses.personalexpenses.domain.repository.UserRepository;

@Component
public class UserDetailsSecurityServer implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<UserAdmin> userModelRepository = userRepository.findByEmail(username);

        if(userModelRepository.isEmpty()) {
            
            throw new UsernameNotFoundException("Usuário ou senha inválidos.");
        }

        return userModelRepository.get();
    }

    
    
}
