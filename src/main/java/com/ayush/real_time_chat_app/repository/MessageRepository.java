package com.ayush.real_time_chat_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.real_time_chat_app.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
