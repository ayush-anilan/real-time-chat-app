package com.ayush.real_time_chat_app.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.real_time_chat_app.service.ChatRoomService;
import com.ayush.real_time_chat_app.service.MessageService;
import com.ayush.real_time_chat_app.web.request.ChatRoomRequest;
import com.ayush.real_time_chat_app.web.response.ChatRoomResponse;
import com.ayush.real_time_chat_app.web.response.ChatRoomsResponse;
import com.ayush.real_time_chat_app.web.response.MessagesResponse;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    public ChatRoomController(ChatRoomService chatRoomService, MessageService messageService) {
        this.chatRoomService = chatRoomService;
        this.messageService = messageService;
    }

    @PostMapping
    public ChatRoomResponse createChatRoom(@Validated @RequestBody ChatRoomRequest request) {
        return chatRoomService.createChatRoom(request);
    }

    @GetMapping
    public ChatRoomsResponse getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/{id}/messages")
    public MessagesResponse getRoomMessages(@PathVariable Long id) {
        return messageService.getMessagesByRoomId(id);
    }
}
