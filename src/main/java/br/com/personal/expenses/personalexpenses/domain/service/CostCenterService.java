package br.com.personal.expenses.personalexpenses.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.personal.expenses.personalexpenses.domain.exception.ResourceNotFoundException;
import br.com.personal.expenses.personalexpenses.domain.model.CostCenter;
import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;
import br.com.personal.expenses.personalexpenses.domain.repository.CostCenterRepository;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.CostCenter.CostCenterResponseDTO;

public class CostCenterService implements CRUDService<CostCenterRequestDTO, CostCenterResponseDTO> {
    
    @Autowired
    private CostCenterRepository costCenterRepository;

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
            throw new ResourceNotFoundException("Centro de custo não encontrado.");
        }

        return mapper.map(costCenterModelRepository.get(), CostCenterResponseDTO.class);
    }

    @Override
    public CostCenterResponseDTO register(CostCenterRequestDTO dto) {

        CostCenter costCenterModel = mapper.map(dto, CostCenter.class);

        // quem é o usuário que faz essa requisição
        UserAdmin user = (UserAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // define que o usuário que criou o centro de custo foi o usuário que fez a requisição
        costCenterModel.setUser(user);

        costCenterModel.setId(null);

        costCenterModel = costCenterRepository.save(costCenterModel);
     
        return mapper.map(costCenterModel, CostCenterResponseDTO.class);
    }

    @Override
    public CostCenterResponseDTO updateById(Long id, CostCenterRequestDTO dto) {

        getById(id);
        CostCenter costCenterModel = mapper.map(dto, CostCenter.class);

        UserAdmin user = (UserAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        costCenterModel.setUser(user);

        costCenterModel.setId(id);
        costCenterModel = costCenterRepository.save(costCenterModel);
      
        return mapper.map(costCenterModel, CostCenterResponseDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        
        getById(id);
        costCenterRepository.deleteById(id);
    }

    
}
