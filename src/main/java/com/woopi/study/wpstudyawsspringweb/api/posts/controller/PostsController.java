package com.woopi.study.wpstudyawsspringweb.api.posts.controller;

import com.woopi.study.wpstudyawsspringweb.api.common.dto.CommonResponse;
import com.woopi.study.wpstudyawsspringweb.api.posts.dto.PostsDto;
import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import com.woopi.study.wpstudyawsspringweb.api.posts.service.PostsService;
import com.woopi.study.wpstudyawsspringweb.enumeration.StatusCode;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Posts")
@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public CommonResponse<PostsDto.SaveResponse> save (@RequestBody PostsDto.SaveRequest saveRequest) {

        try {
            Posts posts = postsService.save(saveRequest);
            return  CommonResponse.<PostsDto.SaveResponse>builder()
                    .statusCode(StatusCode.SUCCESS)
                    .message("저장 성공")
                    .data(PostsDto.SaveResponse.toDto(posts))
                    .build();
        } catch (Exception e) {
            return CommonResponse.<PostsDto.SaveResponse>builder()
                                            .statusCode(StatusCode.UNKNOWN_EXCEPTION)
                                            .build();
        }
    }
}

