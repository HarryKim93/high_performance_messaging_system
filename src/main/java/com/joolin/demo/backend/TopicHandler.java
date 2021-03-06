package com.joolin.demo.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joolin.demo.backend.model.ChattingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TopicHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicHandler.class);

    @Autowired
    private SimpMessagingTemplate template;

    @KafkaListener(id = "test-listener", topics = "Topic1")
    public void receive(ChattingMessage message) throws Exception{
        try {
            LOGGER.info("message='{}'", message);
            HashMap<String, String> msg = new HashMap<>();
            msg.put("timestamp", Long.toString(message.getTimeStamp()));
            msg.put("message", message.getMessage());
            msg.put("author", message.getUser());

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(msg);
            this.template.convertAndSend("/topic/public", json);
        }
        catch (Exception e) {
            LOGGER.info("This Message is Not Proper to Send");
        }
    }
}
