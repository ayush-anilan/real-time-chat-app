package com.ayush.real_time_chat_app.web.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data   
@AllArgsConstructor
public class ChatRoomResponse {
    private String message;
    private String name;
    private String createdAt;
    
}
