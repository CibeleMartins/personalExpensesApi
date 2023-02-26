package br.com.personal.expenses.personalexpenses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.personal.expenses.personalexpenses.domain.service.CostCenterService;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterResponseDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cost_center")
public class CostCenterController {
    
    @Autowired
    private CostCenterService costCenterService;

    @GetMapping
    public ResponseEntity<List<CostCenterResponseDTO>> getAll() {

        return ResponseEntity.ok(costCenterService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostCenterResponseDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(costCenterService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CostCenterResponseDTO> register(@RequestBody CostCenterRequestDTO costCenter) {

        return new ResponseEntity<>(costCenterService.register(costCenter), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostCenterResponseDTO> update(@PathVariable Long id ,@RequestBody CostCenterRequestDTO costCenter) {

        return new ResponseEntity<>(costCenterService.updateById(id,costCenter), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        costCenterService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
