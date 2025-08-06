package com.kama.notes.task.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kama.notes.mapper.MessageMapper;
import com.kama.notes.model.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageTaskConsumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageMapper messageMapper;


    @KafkaListener(topics = "message-task-topic", groupId = "message-consumer-group", concurrency = "5")
    public void onKafkaMessage(String messageTaskJson) throws JsonProcessingException {
        processMessageTask(messageTaskJson);
    }

    private void processMessageTask(String messageTaskJson) throws JsonProcessingException {
        Message message = objectMapper.readValue(messageTaskJson, Message.class);

        messageMapper.insert(message);
    }
}
