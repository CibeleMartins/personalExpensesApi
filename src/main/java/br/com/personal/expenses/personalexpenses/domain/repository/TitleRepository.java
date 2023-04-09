package br.com.personal.expenses.personalexpenses.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.personal.expenses.personalexpenses.domain.model.Title;
import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;

public interface TitleRepository extends JpaRepository<Title, Long> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM public.title " + 
    "WHERE date_due " + "BETWEEN TO_TIMESTAMP(:periodInitial, 'YYYY-MM-DD hh24:MI:SS') AND " +
    "TO_TIMESTAMP(:periodFinal, 'YYYY-MM-DD hh24:MI:SS')")
    List<Title> getCashFlowByDateDue(@Param("periodInitial") String periodInitial, @Param("periodFinal") String periodFinal);

    List<Title> findByUser(UserAdmin user);
}
