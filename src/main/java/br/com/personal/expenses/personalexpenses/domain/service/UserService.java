package br.com.personal.expenses.personalexpenses.domain.service;

import java.util.List;

import br.com.personal.expenses.personalexpenses.dto.User.UserRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.User.UserResponseDTO;

public class UserService implements CRUDService<UserRequestDTO, UserResponseDTO> {

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<UserResponseDTO> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDTO getById() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDTO register(UserRequestDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponseDTO updateById(Long id, UserRequestDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
