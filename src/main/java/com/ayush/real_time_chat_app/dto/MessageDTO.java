package com.ayush.real_time_chat_app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private String content;
    private String sender;
    private Long roomId;
    private LocalDateTime sentAt;
}
