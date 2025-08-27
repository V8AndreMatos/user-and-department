package com.department.user.dto;

import com.department.user.entities.User;

public class UserDTO {

    private Long id;
    private String name;
    private String email;

    public UserDTO(User userEntity) {

        id = userEntity.getId();
        name = userEntity.getName();
        email = userEntity.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
