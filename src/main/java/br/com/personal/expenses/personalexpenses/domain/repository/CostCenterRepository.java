package br.com.personal.expenses.personalexpenses.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CostCenterRepository extends JpaRepository<CostCenterRepository, Long> {
    
}
