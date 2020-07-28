package com.joolin.demo.backend.Controller;

import com.joolin.demo.backend.TopicHandler;
import com.joolin.demo.backend.Sender;
import com.joolin.demo.backend.model.ChattingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class ChattingController {
    @Autowired
    private Sender sender;

    @Autowired
    private TopicHandler topicHandler;
//
//    @Autowired
//    private ChattingHistory chattingHistory;

    private static String TEST_TOPIC="Topic1";

    @MessageMapping("/message")
    public void sendMessage(ChattingMessage message) throws Exception{
        message.setTimeStamp(System.currentTimeMillis());
//        chattingHistory.save(message);
        sender.send(TEST_TOPIC, message);
    }

//    @RequestMapping("/history")
//    public ArrayList<ChattingMessage> getChattingHistory() throws Exception{
//        System.out.println("History:\n");
//        return chattingHistory.get();
//    }

    @MessageMapping("/file")
    @SendTo("/topic/chatting")  //구독중인 주소에 Send
    public ChattingMessage sendFile(ChattingMessage message) throws Exception{
        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
    }
}
