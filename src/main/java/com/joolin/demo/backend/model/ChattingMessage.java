package com.joolin.demo.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@ToString
@RedisHash("ChattingMessage")
public class ChattingMessage implements Serializable {

    @Id
    private String ChatRoomId;

    private String user;
    private String message;
    private Long timeStamp;

    private String fileName;
    private String rawData;

    public ChattingMessage(){
        message = "";
        user = "";
        timeStamp = System.currentTimeMillis();
        fileName = "";
        rawData = "";
    }


    public ChattingMessage(String message, String user){
        this.user = user;
        this.message = message;
    }

    @Builder
    public ChattingMessage(String chatRoomId, String message, String user, Long timeStamp){
        this.ChatRoomId = chatRoomId;
        this.user = user;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public ChattingMessage(String fileName, String rawData, String user){
        this.fileName = fileName;
        this.rawData = rawData;
        this.user = user;
    }

    public ChattingMessage(String message){
        this.message = message;
    }
}
