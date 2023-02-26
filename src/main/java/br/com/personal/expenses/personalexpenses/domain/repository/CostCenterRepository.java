package br.com.personal.expenses.personalexpenses.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.expenses.personalexpenses.domain.model.CostCenter;

public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
    
}
