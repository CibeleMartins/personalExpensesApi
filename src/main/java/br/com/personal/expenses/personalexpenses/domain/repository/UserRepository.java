package br.com.personal.expenses.personalexpenses.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.expenses.personalexpenses.domain.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    
    // todos as propriedades do usuário podem se tornar um método de pesquisa no bd
    // Nesse caso, vamos precisar desse método no momento da autenticação, 
    // porque vamos precisar saber quem é o usuário no bd pelo email
    List<User> findByEmail(String email);
}
