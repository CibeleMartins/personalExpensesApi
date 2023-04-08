package br.com.personal.expenses.personalexpenses.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.personal.expenses.personalexpenses.domain.exception.ResourceBadRequestException;
import br.com.personal.expenses.personalexpenses.domain.exception.ResourceNotFoundException;
import br.com.personal.expenses.personalexpenses.domain.model.Title;
import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;
import br.com.personal.expenses.personalexpenses.domain.repository.TitleRepository;
import br.com.personal.expenses.personalexpenses.dto.Title.TitleRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.Title.TitleResponseDTO;

public class TitleService implements CRUDService<TitleRequestDTO, TitleResponseDTO> {

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<TitleResponseDTO> getAll() {

        List<Title> titles = titleRepository.findAll();

        List<TitleResponseDTO> titlesResponse = titles.stream().map(t -> mapper.map(t, TitleResponseDTO.class)).collect(Collectors.toList());

        return titlesResponse;
    }

    @Override
    public TitleResponseDTO getById(Long id) {
        Optional<Title> titles = titleRepository.findById(id);

        if(titles.isEmpty()) {
            throw new ResourceNotFoundException("Centro de custo não encontrado.");
        }

        return mapper.map(titles.get(), TitleResponseDTO.class);
    }

    @Override
    public TitleResponseDTO register(TitleRequestDTO dto) {

        validateTitle(dto);
        Title titleModel = mapper.map(dto, Title.class);

        // quem é o usuário que faz essa requisição
        UserAdmin user = (UserAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // define que o usuário que criou o centro de custo foi o usuário que fez a requisição
        titleModel.setUser(user);

        titleModel.setId(null);

        titleModel = titleRepository.save(titleModel);
     
        return mapper.map(titleModel, TitleResponseDTO.class);
    }

    @Override
    public TitleResponseDTO updateById(Long id, TitleRequestDTO dto) {
        getById(id);
        validateTitle(dto);
        Title titleModel = mapper.map(dto, Title.class);

        UserAdmin user = (UserAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        titleModel.setUser(user);

        titleModel.setId(id);
        titleModel = titleRepository.save(titleModel);
      
        return mapper.map(titleModel, TitleResponseDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        getById(id);
        titleRepository.deleteById(id);
    }
    

    private void validateTitle(TitleRequestDTO titleDto) {
        
        if(titleDto.getType() == null || titleDto.getDateDue() == null || titleDto.getValue() == null || titleDto.getDescription() == null) {
            throw new ResourceBadRequestException("os campos Tipo, Data de Vencimento, Valor e Descrição são obrigatórios.");
        }
    }
}
