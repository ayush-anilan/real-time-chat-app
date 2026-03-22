package com.ayush.real_time_chat_app.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ayush.real_time_chat_app.dto.MessageDTO;
import com.ayush.real_time_chat_app.model.ChatRoom;
import com.ayush.real_time_chat_app.model.Message;
import com.ayush.real_time_chat_app.model.User;
import com.ayush.real_time_chat_app.repository.ChatRoomRepository;
import com.ayush.real_time_chat_app.repository.MessageRepository;
import com.ayush.real_time_chat_app.repository.UserRepository;

@Controller
public class ChatController {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatController(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public MessageDTO sendMessage(@DestinationVariable Long roomId, @Payload MessageDTO messageDTO){

        User sender = userRepository.findByUsername(messageDTO.getSender()).orElseThrow(() -> new RuntimeException("User not found"));

        ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Chat room not found"));

        Message message = new Message();

        message.setContent(messageDTO.getContent());
        message.setSender(sender);
        message.setRoom(room);
        message.setSentAt(LocalDateTime.now());

        messageRepository.save(message);

        messageDTO.setSentAt(message.getSentAt());
        return messageDTO;
    }

}
