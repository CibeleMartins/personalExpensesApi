package br.com.personal.expenses.personalexpenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.personal.expenses.personalexpenses.domain.service.DashboardService;
import br.com.personal.expenses.personalexpenses.dto.Dashboard.DashboardResponseDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cashFlow")
public class DashboardController {
    
    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> getCashFlow(@RequestParam(name = "periodInitial") String periodInitial,@RequestParam(name = "periodFinal") String periodFinal) {
        return ResponseEntity.ok(dashboardService.getCashFlow(periodInitial, periodFinal));
    }
}
