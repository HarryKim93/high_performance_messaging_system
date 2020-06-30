package com.joolin.demo.backend;

import com.joolin.demo.backend.Data.ChattingHistory;
import com.joolin.demo.backend.model.ChattingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class JoolinApplicationRunner implements ApplicationRunner {
    @Autowired
    Sender sender;

    @Autowired
    ChattingHistory chattingHistory;

    @Override
    public void run(ApplicationArguments arguments) throws Exception{

        ChattingMessage chattingMessage = new ChattingMessage();
        chattingMessage.setMessage("Hello World!!!");
        chattingMessage.setTimeStamp(System.currentTimeMillis());
        chattingMessage.setUser("???");
        sender.send("Topic1", chattingMessage);
    }
}
