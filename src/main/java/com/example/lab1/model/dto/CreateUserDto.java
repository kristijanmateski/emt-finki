package com.example.lab1.model.dto;

import com.example.lab1.model.Enum.UserRole;
import com.example.lab1.model.UserEntity;

public record CreateUserDto(String username, String password, UserRole role) {
    public UserEntity toUser(){ return new UserEntity(username,password,role);}
}
