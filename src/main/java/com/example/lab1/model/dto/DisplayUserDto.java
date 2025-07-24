package com.example.lab1.model.dto;

import com.example.lab1.model.Enum.UserRole;
import com.example.lab1.model.UserEntity;

public record DisplayUserDto(String username, String password, UserRole role) {
    public static DisplayUserDto fromUserToDisplayUserDto(UserEntity user) {
        return new DisplayUserDto(user.getUsername(), user.getPassword(), user.getRole());
    }
}
