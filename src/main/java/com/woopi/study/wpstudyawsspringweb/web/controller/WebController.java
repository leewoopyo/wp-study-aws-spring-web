package com.woopi.study.wpstudyawsspringweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/swagger-ui")
    public String swagger() {
        return "redirect:/swagger-ui/index.html";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}

