package com.department.user.controllers;

import com.department.user.dto.UserDTO;
import com.department.user.entities.User;
import com.department.user.repositories.UserRepository;
import com.department.user.service.UserService;
import com.department.user.exceptions.ResourceNotFoundException;
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
       try {
           userService.findById(id);
           return new ResponseEntity<String>(" Usuario com o id " +id+ " encontrado com sucesso" , HttpStatus.OK);
       } catch (ResourceNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("ID NÃO EXISTE");
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body("Erro ao deletar usuário e ID : " +id+ " inexistente");
       }

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
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok("Usuário com ID " + id + " deletado com sucesso.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID NÃO EXISTE");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao deletar usuário");
        }
    }

    }

