package com.department.user.controllers;

import com.department.user.dto.UserDTO;
import com.department.user.entities.User;
import com.department.user.repositories.UserRepository;
import com.department.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Metodo para trazer uma lista de usuários
    @GetMapping
    public List<UserDTO>findAll() {
        return userService.findAll();
    }

    // Metodo para trazer um usuário por ID
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<String> findById(@PathVariable Long id){
       userService.findById(id);
        return new ResponseEntity<String>(" Usuario com o id " +id+ " encontrado com sucesso" , HttpStatus.OK);

    }

    // Metodo para inserir um novo usuário
    @PostMapping
    @ResponseBody
    public ResponseEntity<User> insertNewUser(@RequestBody User  userDTO){
        // Converter DTO para Entity
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        // Salvar
        User savedUser = userService.saveUser(user);

        return ResponseEntity.ok(savedUser);
    }

    // Metodo para deletar um usuário por ID
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<String>("Usuário com o id :  " +id+ " Deletado com sucesso" , HttpStatus.OK);
    }

    }

