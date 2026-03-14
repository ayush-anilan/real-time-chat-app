package com.ayush.real_time_chat_app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.real_time_chat_app.service.UserService;
import com.ayush.real_time_chat_app.web.request.LoginRequest;
import com.ayush.real_time_chat_app.web.request.RegisterRequest;
import com.ayush.real_time_chat_app.web.response.LoginResponse;
import com.ayush.real_time_chat_app.web.response.RegisterResponse;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public RegisterResponse createUser(@RequestBody RegisterRequest request){
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest request){
        return userService.loginUser(request);
    }
}
