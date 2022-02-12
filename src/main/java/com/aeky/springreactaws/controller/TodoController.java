package com.aeky.springreactaws.controller;

import com.aeky.springreactaws.dto.ResponseDto;
import com.aeky.springreactaws.dto.TodoDto;
import com.aeky.springreactaws.model.TodoEntity;
import com.aeky.springreactaws.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/test")
    public ResponseEntity<?> getTodoTest() {
        String str = todoService.test();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDto<String> response = ResponseDto.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodo() {
        String tempUserId = "temp-user";

        List<TodoEntity> entities = todoService.retrieve(tempUserId);
        List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());
        ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto dto) {
        try {
            String tempUserId = "temp-user";

            // DTO --> 엔티티
            TodoEntity entity = TodoDto.toEntity(dto);
            entity.setId(null); // id 초기화
            entity.setUserId(tempUserId);

            // 엔티티 저장
            List<TodoEntity> entities = todoService.create(entity);

            // 엔티티 --> DTO
            // 응답 생성
            List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDto dto) {
        String tempUserId = "temp-user";

        TodoEntity entity = TodoDto.toEntity(dto);
        entity.setUserId(tempUserId);

        List<TodoEntity> entities = todoService.update(entity);
        List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());

        ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDto dto) {
        try {
            String tempUserId = "temp-user";
            TodoEntity entity = TodoDto.toEntity(dto);
            entity.setUserId(tempUserId);

            List<TodoEntity> entities = todoService.delete(entity);
            List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());

            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch(Exception e) {
            String error = e.getMessage();
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
