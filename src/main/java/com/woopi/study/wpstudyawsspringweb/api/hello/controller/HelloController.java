package com.woopi.study.wpstudyawsspringweb.api.hello.controller;

import com.woopi.study.wpstudyawsspringweb.api.hello.dto.HelloDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hello")
@RestController
public class HelloController {

    @ApiOperation("Hello Api 테스트")
    @GetMapping("/hello")
    public String hello () {
        return "Hello World";
    }

    @ApiOperation("Hello Api Dto 테스트")
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

