package br.com.personal.expenses.personalexpenses.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.personal.expenses.personalexpenses.domain.model.CostCenter;
import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;

public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
    
    List<CostCenter> findByUser(UserAdmin user);
}
