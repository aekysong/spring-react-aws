package com.aeky.springreactaws.controller;

import com.aeky.springreactaws.dto.ResponseDto;
import com.aeky.springreactaws.dto.TestRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("/sayhi")
    public String getTestResponse() {
        return "Say Hello To World!";
    }

    @GetMapping("/{id}")
    public String getTestResponse2(@PathVariable(required = false) int id) {
        return id + ", hello!";
    }

    @GetMapping("/param")
    public String getTestResponse3(@RequestParam(required = false) int id) {
        return id + ", hi!";
    }

    @GetMapping("/reqdto")
    public String getTestResponse4(@RequestBody TestRequestDto testRequestDto) {
        return "Hi! This is " + testRequestDto.getId() + " and wants to send " + testRequestDto.getMessage();
    }

    @GetMapping("/resdto")
    public ResponseDto<String> getTestResponse5() {
        List<String> list = new ArrayList<>();
        list.add("This is request DTO.");
        ResponseDto<String> response = ResponseDto.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/resent")
    public ResponseEntity<?> getTestResponse6() {
        List<String> list = new ArrayList<>();
        list.add("Let's try Response Entity.");
        ResponseDto<String> response = ResponseDto.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(response);
    }
    // ResponseDto와 ResponseEntity를 리턴하는 것의 차이점 -> ResponseEntity는 헤더, HTTP Status 조작 가능
}
