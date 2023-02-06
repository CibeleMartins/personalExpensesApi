package br.com.personal.expenses.personalexpenses.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.personal.expenses.personalexpenses.domain.exception.ResourceNotFoundException;
import br.com.personal.expenses.personalexpenses.domain.model.User;
import br.com.personal.expenses.personalexpenses.domain.repository.UserRepository;
import br.com.personal.expenses.personalexpenses.dto.User.UserRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.User.UserResponseDTO;

public class UserService implements CRUDService<UserRequestDTO, UserResponseDTO> {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void deleteById(Long id) {
       
        getById(id);

       userRepository.deleteById(id);

    }

    @Override
    public List<UserResponseDTO> getAll() {
       
        // pega os usuários do BD
        List<User> usersModel = userRepository.findAll();

        List<UserResponseDTO> usersDto = usersModel.stream().map(u -> mapper.map(u, UserResponseDTO.class)).collect(Collectors.toList());
        
        return usersDto;
    }

    @Override
    public UserResponseDTO getById(Long id) {

        Optional<User> optionalUserModel = userRepository.findById(id);

        if(optionalUserModel.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado.");
        }

        UserResponseDTO userResponseDto = mapper.map(optionalUserModel.get(), UserResponseDTO.class);

        return userResponseDto;
    }

    @Override
    public UserResponseDTO register(UserRequestDTO dto) {

        User userModel = mapper.map(dto, User.class);

        userModel.setId(null);
        
        userModel = userRepository.save(userModel);

        UserResponseDTO userResponse = mapper.map(userModel, UserResponseDTO.class);

        return userResponse;
    }

    @Override
    public UserResponseDTO updateById(Long id, UserRequestDTO dto) {

        getById(id);

        User userModel = mapper.map(dto, User.class);
        
        userModel.setId(id);

        userModel = userRepository.save(userModel);

        UserResponseDTO userResponse = mapper.map(userModel, UserResponseDTO.class);

        return userResponse;
    }
    
}
