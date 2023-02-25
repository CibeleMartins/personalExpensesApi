package br.com.personal.expenses.personalexpenses.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;

// não foi necessário adcicionar anotação pq uma interface consegue herdar a anotação do jpa que é uma outra interface
public interface UserRepository extends JpaRepository<UserAdmin,Long> {
    
    
    // todos as propriedades do usuário podem se tornar um método de pesquisa no bd
    // Nesse caso, vamos precisar desse método no momento da autenticação, 
    // porque vamos precisar saber quem é o usuário no bd pelo email
    Optional<UserAdmin> findByEmail(String email);
}
