package com.woopi.study.wpsdutyawsspringweb.api.hello.controller;

import com.woopi.study.wpsdutyawsspringweb.api.hello.dto.HelloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello () {
        return "Hello World";
    }

    @GetMapping("/hello/dto")
    public HelloDto.Response helloDto (
            @RequestParam("name") String name,
            @RequestParam("amount") int amount
    ) {
        return HelloDto.Response.builder()
                                .name(name)
                                .amount(amount)
                                .build();
    }

}

