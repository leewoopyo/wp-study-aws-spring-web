package com.woopi.study.wpsdutyawsspringweb.api.hello.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/* https://scshim.tistory.com/317  >  WebMvcTest, AutoConfigureMockMvc 관련 자료 */

@ExtendWith(SpringExtension.class) /* pring 프레임워크를 사용하여 테스트할 때 필요한 확장 기능을 제공, Spring 애플리케이션 컨텍스트와 테스트 라이프사이클 관리를 제공하는 어노테이션, Spring 관련 어노테이션(@Autowired 등)을 사용할 수 있게 된다 */
//@WebMvcTest (controllers = CommonController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class HelloControllerTest {

    /**
     * MockMvc는 웹 어플리케이션을 애플리케이션 서버에 배포하지 않고 테스트용 MVC환경을 만들어 요청 및 전송, 응답기능을 제공해주는 유틸리티 클래스다.
     */
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("hello world api 테스트")
    @Test
    public void helloApiTest() throws Exception {
        //1. hello api 호출
        //2. 200응답인지 검증
        //3. 리턴 데이터가 'Hello World'인지
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @DisplayName("hello world api dto 테스트")
    @Test
    public void helloApiDtoTest() throws Exception {
        //1. hello/dto api 호출
        //2. 200응답인지 검증
        //3. 응답 데이터 검증

        String name = "hello";
        int amount = 1000;

        mockMvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) //param은 문자열만 가능
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //jsonPath : json응답값을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.name", is(name))); //$를 기준으로 필드명을 명시한다
    }

}
