package com.joolin.demo.backend.Data;

import com.joolin.demo.backend.model.ChattingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class ChattingHistory {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ChattingHistoryRepository chattingHistoryRepository;

    public void save(ChattingMessage chatObj){
        this.chattingHistoryRepository.save(chatObj);
    }

    public void clear(){
        this.chattingHistoryRepository.deleteAll();
    }

//    public List<ChattingMessage> get() {
//        return chattingHistoryRepository.findAll().forEach();
//    }


//    private final Cache<UUID, ChattingMessage> chatHistoryCache = CacheBuilder
//            .newBuilder().maximumSize(20).expireAfterWrite(10, TimeUnit.MINUTES)
//            .build();
//
//    public List<ChattingMessage> get(){
//        return chatHistoryCache.asMap().values().stream()
//                .sorted(Comparator.comparing(ChattingMessage::getTimeStamp))
//                .collect(Collectors.toList());
//    }
}
