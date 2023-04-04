package com.woopi.study.wpstudyawsspringweb.pages.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {
    @GetMapping("/swagger-ui")
    public String swagger() {
        return "redirect:/swagger-ui/index.html";
    }

}

