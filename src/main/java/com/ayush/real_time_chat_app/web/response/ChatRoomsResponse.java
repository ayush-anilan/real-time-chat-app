package com.ayush.real_time_chat_app.web.response;

import java.util.List;

import com.ayush.real_time_chat_app.model.ChatRoom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatRoomsResponse {
    
    private String message;
    private List<ChatRoom> chatRooms;
}
