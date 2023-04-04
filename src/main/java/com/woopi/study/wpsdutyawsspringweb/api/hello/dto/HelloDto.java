package com.woopi.study.wpsdutyawsspringweb.api.hello.dto;

import lombok.*;

public class HelloDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @ToString
    public static class Request {

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Response {
        private String name;
        private int amount;
    }

}
