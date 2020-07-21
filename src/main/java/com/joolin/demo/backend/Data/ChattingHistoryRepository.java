package com.joolin.demo.backend.Data;

import com.joolin.demo.backend.model.ChattingMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChattingHistoryRepository extends CrudRepository<ChattingMessage, String> { }
