package com.woopi.study.wpstudyawsspringweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 다음과 같은 원인으로 인해 아래 설정을 추가함
 * https://velog.io/@tkaltk123/%EB%B6%80%ED%8A%B8%EC%8A%A4%ED%8A%B8%EB%9E%A9-%EB%A6%AC%EC%86%8C%EC%8A%A4%EB%A5%BC-%EB%B6%88%EB%9F%AC%EC%98%A4%EC%A7%80-%EB%AA%BB%ED%95%98%EB%8A%94-%EB%AC%B8%EC%A0%9C
 * @EnableWebMvc 가 뭐하는 어노테이션인지 확인할 필요가 있음
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
