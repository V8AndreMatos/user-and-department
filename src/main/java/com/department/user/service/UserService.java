package com.department.user.service;

import com.department.user.dto.UserDTO;
import com.department.user.entities.User;
import com.department.user.exceptions.ResourceNotFoundException;
import com.department.user.exceptions.DatabaseException;
import org.springframework.dao.DataIntegrityViolationException;
import com.department.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> result = userRepository.findAll();
        List<UserDTO> dtoList = result.stream().map(x -> new UserDTO(x)).toList();
        return dtoList;
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User result = userRepository.findById(id).get();
        UserDTO userDTO = new UserDTO(result);
        return userDTO;
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("ID NÃO EXISTE");
        }
        userRepository.deleteById(id);

   }


}