package com.woopi.study.wpstudyawsspringweb.api.hello.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloDtoTest {

    @Test
    public void dtoTest() {
        /**
         * given(준비)-when(실행)-then(검증) 패턴
         * https://brunch.co.kr/@springboot/292
         */

        //given(준비)
        String name = "test";
        int amount = 1000;

        //when(실행)
        /**
         * builder 패턴
         * https://sudo-minz.tistory.com/133
         */
        HelloDto.Response helloResponseDto = HelloDto.Response.builder()
                                                                .name(name)
                                                                .amount(amount)
                                                                .build();
        //then(검증)
        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }

}
