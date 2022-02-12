package com.aeky.springreactaws.service;

import com.aeky.springreactaws.model.TodoEntity;
import com.aeky.springreactaws.persistence.TodoRepository;
import com.sun.tools.javac.comp.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j  // 로깅
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

    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        // 엔티티 유효성 검증
        validate(entity);

        // 엔티티 DB 저장
        todoRepository.save(entity);
        log.info("Entity ID: {} is saved.", entity.getId());

        // 저장된 엔티티를 포함해 리스트 리턴
        return todoRepository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId) {
        return todoRepository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);

        final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            todoRepository.save(todo);
            log.info("Entity ID: {} is modified.", entity.getId());
        });

        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);

        try {
            todoRepository.delete(entity);
        } catch(Exception e) {
            log.error("error deleting entity ", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }

        return retrieve(entity.getUserId());
    }
}
