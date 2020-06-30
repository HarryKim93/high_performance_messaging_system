package com.joolin.demo.backend.Controller;

import com.joolin.demo.backend.Data.ChattingHistory;
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
    private ChattingHistory chattingHistory;

    private static String BOOT_TOPIC="Topic1";

    @MessageMapping("/message")
    @SendTo("/topic/public")
    public void sendMessage(ChattingMessage message) throws Exception{
        message.setTimeStamp(System.currentTimeMillis());
        chattingHistory.save(message);
        sender.send(BOOT_TOPIC, message);
    }

    @RequestMapping("/history")
    public List<ChattingMessage> getChattingHistory() throws Exception{
        System.out.println("History:\n");
        return chattingHistory.get();
    }

    @MessageMapping("/file")
    @SendTo("/topic/chatting")
    public ChattingMessage sendFile(ChattingMessage message) throws Exception{
        return new ChattingMessage(message.getFileName(), message.getRawData(), message.getUser());
    }
}
