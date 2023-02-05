package br.com.personal.expenses.personalexpenses.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    
    // c/ essa anotação é possível chamar este método por inversão de controle
    // cria a dependencia e passa pro spring, e qunado precisar de um model mapper
    // pode ser usado o @Autowired p/ fazer a inversão de controle

    // em palavras simples, essa anotação @Bean permite utilizar a instancia de uma classe
    // em toda aplicação sem precisar fazer isso manualmente sempre que precisar dela
    
    @Bean
    public ModelMapper mapper () {
        return new ModelMapper();
    }
}
