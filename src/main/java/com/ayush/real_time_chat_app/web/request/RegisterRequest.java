package com.ayush.real_time_chat_app.web.request;

import lombok.Data;

@Data
public class RegisterRequest {
    
    private String username;
    private String email;
    private String password;
}
