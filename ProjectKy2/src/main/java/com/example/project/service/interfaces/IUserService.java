package com.example.project.service.interfaces;

import com.example.project.entity.User;
import com.example.project.model.dto.UserDto;
import com.example.project.model.request.RegisterRequest;

import javax.servlet.http.HttpServletRequest;

public interface IUserService{
    public User createUser(RegisterRequest request);

    String getUsername(HttpServletRequest request);

    void hashPassword(Long id);
}
