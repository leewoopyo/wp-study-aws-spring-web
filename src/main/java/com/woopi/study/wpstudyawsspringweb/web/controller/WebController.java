package com.woopi.study.wpstudyawsspringweb.web.controller;

import com.woopi.study.wpstudyawsspringweb.api.posts.dto.PostsDto;
import com.woopi.study.wpstudyawsspringweb.api.posts.service.PostsService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class WebController {

    private final PostsService postsService;

    /**
     * 스웨거
     */
    @GetMapping("/swagger-ui")
    public String swagger() {
        return "redirect:/swagger-ui/index.html";
    }

    /**
     * 메인페이지, 게시글 조회
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllByDesc());
        return "index";
    }

    /**
     * 게시글등록
     */
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts/posts-save";
    }

    /**
     * 게시글수정
     */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsDto.GetResponse dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts/posts-update";
    }

}

