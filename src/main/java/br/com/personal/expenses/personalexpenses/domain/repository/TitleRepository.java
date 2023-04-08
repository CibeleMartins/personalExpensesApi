package br.com.personal.expenses.personalexpenses.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.expenses.personalexpenses.domain.model.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {
    
}
