package com.example.project.service.classes;

import com.example.project.configuration.JwtTokenUtil;
import com.example.project.entity.Role;
import com.example.project.entity.User;
import com.example.project.model.dto.UserDto;
import com.example.project.model.request.RegisterRequest;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.interfaces.IUserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final String LOGIN_COOKIE="JWT_TOKEN";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(RegisterRequest request) {
        User user=new User();
//        user.setId(2L);
        user.setEmail(request.getEmail());
        user.setFullname(request.getFullname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role=roleRepository.findById(2L).get();
        user.addRole(role);
        return repository.save(user);
    }

    @Override
    public String getUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (var cookie : cookies) {
                if (cookie.getName().equals(LOGIN_COOKIE)) {
                    return jwtTokenUtil.extractUsername(cookie.getValue());
                }
            }
        }
        return null;
    }

    @Override
    public void hashPassword(Long id){
        User user=repository.findById(id).get();
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        repository.save(user);
    }
}
