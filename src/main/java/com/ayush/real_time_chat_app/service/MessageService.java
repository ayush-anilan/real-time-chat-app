package com.ayush.real_time_chat_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayush.real_time_chat_app.model.ChatRoom;
import com.ayush.real_time_chat_app.model.Message;
import com.ayush.real_time_chat_app.repository.ChatRoomRepository;
import com.ayush.real_time_chat_app.repository.MessageRepository;
import com.ayush.real_time_chat_app.web.response.MessagesResponse;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public MessageService(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public MessagesResponse getMessagesByRoomId(Long roomId) {
        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        List<Message> messages = messageRepository.findByRoomIdOrderBySentAtAsc(roomId);
        return new MessagesResponse("Messages fetched successfully", messages);
    }
}
