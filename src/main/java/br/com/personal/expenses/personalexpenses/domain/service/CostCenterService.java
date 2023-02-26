package br.com.personal.expenses.personalexpenses.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.personal.expenses.personalexpenses.domain.exception.ResourceNotFoundException;
import br.com.personal.expenses.personalexpenses.domain.model.CostCenter;
import br.com.personal.expenses.personalexpenses.domain.repository.CostCenterRepository;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterResponseDTO;

public class CostCenterService implements CRUDService<CostCenterRequestDTO, CostCenterResponseDTO> {
    
    @Autowired
    private CostCenterService costCenterService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CostCenterResponseDTO> getAll() {
       
        List<CostCenter> costsCentersRepository = costCenterRepository.findAll();

        return costsCentersRepository.stream().map(costCenter -> mapper.map(costCenter, CostCenterResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CostCenterResponseDTO getById(Long id) {
       
        Optional<CostCenter> costCenterModelRepository = costCenterRepository.findById(id);

        if(costCenterModelRepository.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado.");
        }

        return mapper.map(costCenterModelRepository.get(), CostCenterResponseDTO.class);
    }

    @Override
    public CostCenterResponseDTO register(CostCenterRequestDTO dto) {

        dto.setId(null);

        CostCenter costCenterModel = mapper.map(dto, CostCenter.class);

        costCenterModel.setId(null);

        costCenterModel = costCenterRepository.save(costCenterModel);
     
        return mapper.map(costCenterModel, CostCenterResponseDTO.class);
    }

    @Override
    public CostCenterResponseDTO updateById(Long id, CostCenterRequestDTO dto) {
      
        return null;
    }

    @Override
    public void deleteById(Long id) {
        
    }

    private CostCenterRepository costCenterRepository;
}
