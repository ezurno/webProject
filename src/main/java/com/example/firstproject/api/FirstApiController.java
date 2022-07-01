package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //RestAPI 컨트롤러 JSON을 반환 하기 위함
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world!";
    }
}
