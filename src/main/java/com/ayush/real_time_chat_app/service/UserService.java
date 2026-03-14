package com.ayush.real_time_chat_app.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayush.real_time_chat_app.config.JwtUtil;
import com.ayush.real_time_chat_app.model.User;
import com.ayush.real_time_chat_app.repository.UserRepository;
import com.ayush.real_time_chat_app.web.request.LoginRequest;
import com.ayush.real_time_chat_app.web.request.RegisterRequest;
import com.ayush.real_time_chat_app.web.response.LoginResponse;
import com.ayush.real_time_chat_app.web.response.RegisterResponse;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public RegisterResponse registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); 
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new RegisterResponse("User registered successfully", token, user.getEmail(), user.getUsername());
    }

    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new LoginResponse("Login successful", token, user.getEmail(), user.getUsername());
    }
}
