package com.aeky.springreactaws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("todo")
public class TodoController {
    @GetMapping("/test")
    public ResponseEntity<?> getTodoTest() {
        return ResponseEntity.ok().body("OK!");
    }
}
