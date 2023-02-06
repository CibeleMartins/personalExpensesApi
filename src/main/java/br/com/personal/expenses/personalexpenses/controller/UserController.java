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

import br.com.personal.expenses.personalexpenses.domain.service.UserService;
import br.com.personal.expenses.personalexpenses.dto.User.UserRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.User.UserResponseDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users_admin")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {

        return ResponseEntity.ok(userService.getAll());
    }

       
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userDto){

        UserResponseDTO userRegistered = userService.register(userDto);

        return new ResponseEntity<>(userRegistered, HttpStatus.CREATED);    
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> register(@PathVariable Long id, @RequestBody UserRequestDTO userDto){

        UserResponseDTO userRegistered = userService.updateById(id ,userDto);

        return new ResponseEntity<>(userRegistered, HttpStatus.OK);    
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {

        userService.deleteById(id);

    }

}
