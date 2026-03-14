package com.ayush.real_time_chat_app.web.request;

import lombok.Data;

@Data
public class LoginRequest {
    
    private String email;
    private String password;
}
