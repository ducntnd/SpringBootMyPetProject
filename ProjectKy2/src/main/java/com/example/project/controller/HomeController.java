package com.example.project.controller;

import com.example.project.configuration.CustomUserDetails;
import com.example.project.configuration.JwtTokenUtil;
import com.example.project.configuration.JwtUserDetailsService;
import com.example.project.entity.User;
import com.example.project.exception.BadRequestException;
import com.example.project.model.dto.UserDto;
import com.example.project.model.mapper.UserMapper;
import com.example.project.model.request.LoginRequest;
import com.example.project.model.request.RegisterRequest;
import com.example.project.service.interfaces.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.project.utils.Constant.MAX_AGE_COOKIE;

@Controller
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private IUserService userService;

    ObjectMapper objectMapper=new ObjectMapper();


    @GetMapping("/")
    public String home(Model model, HttpServletRequest request,HttpServletResponse response){
        CustomUserDetails userDetails= (CustomUserDetails) userDetailsService.loadUserByUsername(userService.getUsername(request));
        if(userDetails != null){
            ModelMapper modelMapper = new ModelMapper();
            UserDto userDto = modelMapper.map(userDetails.getUser(), UserDto.class);
            model.addAttribute("user",userDto);
        }
        Cookie cookie=new Cookie("contextPath", "/");
        cookie.setPath("/");
        response.addCookie(cookie);
        return Route.index;
    }

    @PostMapping(value = "/api/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req, HttpServletResponse response) throws JsonProcessingException {
        // Create user
        System.out.println(req.getFullname()+' '+req.getEmail()+' '+req.getPassword());
        User user = userService.createUser(req);

        // Gen token
        UserDetails principal = new CustomUserDetails(user);
        String token = jwtTokenUtil.generateToken(principal);

        // Add token to cookie to login
        Cookie cookie = new Cookie("JWT_TOKEN",token);
        cookie.setMaxAge(MAX_AGE_COOKIE);
        cookie.setPath("/");
        response.addCookie(cookie);

        UserDto userDto=UserMapper.INSTANCE.userToUserDto(user);
        return ResponseEntity.ok(objectMapper.writeValueAsString(userDto));
    }

    @PostMapping(value="/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpServletResponse response) {
        // Authenticate
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getEmail(),
                            req.getPassword()
                    )
            );
            // Gen token
            String token = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());
            // Add token to cookie to login
            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setMaxAge(MAX_AGE_COOKIE);
            cookie.setPath("/");
            response.addCookie(cookie);

            User user= ((CustomUserDetails) authentication.getPrincipal()).getUser();
            UserDto userDto=UserMapper.INSTANCE.userToUserDto(user);
            return ResponseEntity.ok(objectMapper.writeValueAsString(userDto));

        } catch (Exception ex) {
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }
    }


}
