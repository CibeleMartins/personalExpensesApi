package br.com.personal.expenses.personalexpenses.domain.service;

import br.com.personal.expenses.personalexpenses.domain.repository.CostCenterRepository;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterResponseDTO;

public class CostCenterService implements CRUDService<CostCenterRequestDTO, CostCenterResponseDTO> {
    
    private CostCenterRepository costCenterRepository;
}
