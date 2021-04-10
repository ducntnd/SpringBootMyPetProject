package com.example.project;

import com.example.project.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private IUserService userService;
    @Override
    public void run(String... args) throws Exception {
//        userService.hashPassword(1L);
    }
}
