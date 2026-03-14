package com.ayush.real_time_chat_app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ayush.real_time_chat_app.model.ChatRoom;
import com.ayush.real_time_chat_app.repository.ChatRoomRepository;
import com.ayush.real_time_chat_app.web.request.ChatRoomRequest;
import com.ayush.real_time_chat_app.web.response.ChatRoomResponse;
import com.ayush.real_time_chat_app.web.response.ChatRoomsResponse;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public ChatRoomResponse createChatRoom(ChatRoomRequest request) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(request.getName());
        chatRoom.setCreatedAt(LocalDateTime.now());
        chatRoomRepository.save(chatRoom);
        return new ChatRoomResponse("Chat room created successfully", chatRoom.getCreatedAt().toString(), chatRoom.getName());
    }

    public ChatRoomsResponse getAllChatRooms() {
        // Implementation to fetch all chat rooms and return as response
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        if (chatRooms == null) {
            return new ChatRoomsResponse("Fetched all chat rooms successfully", chatRooms);
        }
        return new ChatRoomsResponse("Fetched all chat rooms successfully", chatRooms);
    }
    
}
