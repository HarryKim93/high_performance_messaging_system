package com.joolin.demo;

import com.joolin.demo.backend.Data.ChattingHistoryRepository;
import com.joolin.demo.backend.model.ChattingMessage;
import jdk.jfr.Timestamp;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
public class chattingHistoryTest {

    @Autowired
    private ChattingHistoryRepository chattingHistoryRepository;

    @After
    public void clear() throws Exception{
        chattingHistoryRepository.deleteAll();
    }

    @Test
    public void RedisEnrollTest(){
        String id = "930718";
        ChattingMessage chattingMessage = ChattingMessage.builder()
                .chatRoomId(id)
                .user("hyunwoo")
                .message("Hello World")
                .timeStamp(System.currentTimeMillis())
                .build();

        chattingHistoryRepository.save(chattingMessage);

        ChattingMessage chatMessage = chattingHistoryRepository.findById(id).get();
        assertThat(chatMessage.getMessage(), is(equalTo("Hello World")));
    }
}
