package br.com.personal.expenses.personalexpenses.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.personal.expenses.personalexpenses.domain.exception.ResourceNotFoundException;
import br.com.personal.expenses.personalexpenses.domain.model.UserAdmin;
import br.com.personal.expenses.personalexpenses.domain.repository.UserRepository;
import br.com.personal.expenses.personalexpenses.dto.User.UserRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.User.UserResponseDTO;

// é necessário a anotação pq o serviço nao estende a interface, ele implementa
@Service
public class UserService implements CRUDService<UserRequestDTO, UserResponseDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void deleteById(Long id) {

        // refatorou este método p/
        // não deletar o usuário do banco
        // apenas seta a data de inativacao
        // e atualiza esse usuário no banco

       Optional<UserAdmin> userOptModel = userRepository.findById(id);


       if (userOptModel.isEmpty()) {
        throw new ResourceNotFoundException("Não foi possível encontrar o usuário com o id: " + id);
        }

        UserAdmin userModel = userOptModel.get();

        userModel.setDataInativacao(new Date());

        userRepository.save(userModel);
    }

    @Override
    public List<UserResponseDTO> getAll() {

        // pega os usuários do BD
        List<UserAdmin> usersModel = userRepository.findAll();

        List<UserResponseDTO> usersDto = usersModel.stream().map(u -> mapper.map(u, UserResponseDTO.class)).collect(Collectors.toList());

        return usersDto;
    }

    @Override
    public UserResponseDTO getById(Long id) {

        Optional<UserAdmin> optionalUserModel = userRepository.findById(id);

        if (optionalUserModel.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado.");
        }

        UserResponseDTO userResponseDto = mapper.map(optionalUserModel.get(), UserResponseDTO.class);

        return userResponseDto;
    }

    @Override
    public UserResponseDTO register(UserRequestDTO dto) {

        // UserAdmin userModel = mapper.map(dto, UserAdmin.class);

        UserAdmin userModel = mapper.map(dto, UserAdmin.class);
      
        userModel.setId(null);
        userModel.setDataCadastro(new Date());
        userModel = userRepository.save(userModel);

        UserResponseDTO userResponse = mapper.map(userModel, UserResponseDTO.class);

        return userResponse;
    }

    @Override
    public UserResponseDTO updateById(Long id, UserRequestDTO dto) {

        // obtem o usuário pelo id
        UserResponseDTO userDto = getById(id);

        // transforma o usuario request em model
        UserAdmin userModel = mapper.map(dto, UserAdmin.class);

        // seta o id e a data de inatiacao p/ o que ja estava no banco
        userModel.setId(id);
        userModel.setDataInativacao(userDto.getDataInativacao());
        userModel.setDataCadastro(userDto.getDataCadastro());
        // salva no banco
        userModel = userRepository.save(userModel);

        UserResponseDTO userResponse = mapper.map(userModel, UserResponseDTO.class);

        return userResponse;
    }

    // foi criado um método privado para validar o email e senha
    // no método de atualizar e cadastrar
    // não implementei porque vou fazer esse tipo de validação no front end

}
