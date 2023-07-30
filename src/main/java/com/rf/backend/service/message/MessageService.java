package com.rf.backend.service.message;

import com.rf.backend.entity.message.Message;
import com.rf.backend.repository.message.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    MessageRepository messageRepository;
    List<Message> messages=new ArrayList<>();
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public List<Message> getAllMessages(){
        return  messages;
    }
    public void save(Message message){
        messageRepository.save(message);
    }
}
