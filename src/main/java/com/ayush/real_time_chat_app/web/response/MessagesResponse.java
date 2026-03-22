package com.ayush.real_time_chat_app.web.response;

import java.util.List;

import com.ayush.real_time_chat_app.model.Message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessagesResponse {

    private String message;
    private List<Message> messages;
}
