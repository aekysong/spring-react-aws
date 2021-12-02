package com.aeky.springreactaws.service;

import com.aeky.springreactaws.model.TodoEntity;
import com.aeky.springreactaws.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public String test() {
        TodoEntity entity = TodoEntity.builder().title("First Todo").build();
        todoRepository.save(entity);
        TodoEntity savedEntity = todoRepository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }
}
