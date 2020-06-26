package com.joolin.demo.backend.Controller;

import com.joolin.demo.backend.Data.ChattingHistoryDAO;
import com.joolin.demo.backend.Receiver;
import com.joolin.demo.backend.Sender;
import com.joolin.demo.backend.model.ChattingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ChattingController {
    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Autowired
    private ChattingHistoryDAO chattingHistoryDAO;

    private static String BOOT_TOPIC="kafka-chatting";

    @MessageMapping("/message")
    @SendTo("/topic/public")
    public void sendMessage(ChattingMessage message) throws Exception{
        message.setTimeStamp(System.currentTimeMillis());
        chattingHistoryDAO.save(message);
        sender.send(BOOT_TOPIC, message);
    }

    @RequestMapping("/history")
    public List<ChattingMessage> getChattingHistory() throws Exception{
        System.out.println("History:\n");
        return chattingHistoryDAO.get();
    }

    @MessageMapping("/file")
    @SendTo("/topic/chatting")
    public ChattingMessage sendFile(ChattingMessage message) throws Exception{
        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
    }
}
