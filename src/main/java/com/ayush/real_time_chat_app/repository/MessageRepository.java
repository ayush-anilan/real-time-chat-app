package com.ayush.real_time_chat_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayush.real_time_chat_app.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByRoomIdOrderBySentAtAsc(Long roomId);
}
