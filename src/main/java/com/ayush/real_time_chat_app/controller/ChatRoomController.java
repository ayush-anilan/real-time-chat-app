package com.ayush.real_time_chat_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.real_time_chat_app.service.ChatRoomService;
import com.ayush.real_time_chat_app.web.request.ChatRoomRequest;
import com.ayush.real_time_chat_app.web.response.ChatRoomResponse;
import com.ayush.real_time_chat_app.web.response.ChatRoomsResponse;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ChatRoomResponse createChatRoom(@RequestBody ChatRoomRequest request) {
        return chatRoomService.createChatRoom(request);
    }

    @GetMapping
    public ChatRoomsResponse getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }
}
