package com.example.project.controller;

import com.example.project.configuration.CustomUserDetails;
import com.example.project.configuration.JwtUserDetailsService;
import com.example.project.entity.Post;
import com.example.project.model.dto.UserDto;
import com.example.project.model.request.PostRequest;
import com.example.project.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private IUserService userService;

    @GetMapping()
    public String getNews(Model model, HttpServletRequest request){
        CustomUserDetails userDetails= (CustomUserDetails) userDetailsService.loadUserByUsername(userService.getUsername(request));
        if(userDetails != null) {
            ModelMapper modelMapper = new ModelMapper();
            UserDto userDto = modelMapper.map(userDetails.getUser(), UserDto.class);
            model.addAttribute("user",userDto);
            model.addAttribute("post", new PostRequest());
            return Route.post;
        }
        return null;
    }

}
