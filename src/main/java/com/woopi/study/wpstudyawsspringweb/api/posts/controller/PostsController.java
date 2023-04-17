package com.woopi.study.wpstudyawsspringweb.api.posts.controller;

import com.woopi.study.wpstudyawsspringweb.api.common.dto.CommonResponse;
import com.woopi.study.wpstudyawsspringweb.api.posts.dto.PostsDto;
import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import com.woopi.study.wpstudyawsspringweb.api.posts.service.PostsService;
import com.woopi.study.wpstudyawsspringweb.enumeration.StatusCode;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Api(tags = "Posts")
@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public CommonResponse<PostsDto.SaveResponse> save (
            @RequestBody PostsDto.SaveRequest saveRequest
    ) {
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
                                    .message(e.getMessage())
                                    .build();
        }
    }

    @PutMapping("/api/v1/posts/{id}")
    public CommonResponse<PostsDto.UpdateResponse> update (
        @PathVariable Long id,
        @RequestBody PostsDto.UpdateRequest updateRequest
    ) {
        try {
            Posts posts = postsService.update(id, updateRequest);
            return CommonResponse.<PostsDto.UpdateResponse>builder()
                    .statusCode(StatusCode.SUCCESS)
                    .message("수정 성공")
                    .data(PostsDto.UpdateResponse.toDto(posts))
                    .build();
        } catch (EntityNotFoundException enfe) {
            return CommonResponse.<PostsDto.UpdateResponse>builder()
                    .statusCode(StatusCode.USER_INFO_NOT_FOUND)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<PostsDto.UpdateResponse>builder()
                    .statusCode(StatusCode.UNKNOWN_EXCEPTION)
                    .message(e.getMessage())
                    .build();
        }
    }

    @GetMapping("/api/v1/posts/{id}")
    public CommonResponse<PostsDto.GetResponse> findById (
            @PathVariable Long id
    ) {
        try {
            PostsDto.GetResponse response = postsService.findById(id);
            return CommonResponse.<PostsDto.GetResponse>builder()
                    .statusCode(StatusCode.SUCCESS)
                    .data(response)
                    .build();
        } catch (EntityNotFoundException enfe) {
            return CommonResponse.<PostsDto.GetResponse>builder()
                    .statusCode(StatusCode.USER_INFO_NOT_FOUND)
                    .build();
        } catch (Exception e) {
            return CommonResponse.<PostsDto.GetResponse>builder()
                    .statusCode(StatusCode.UNKNOWN_EXCEPTION)
                    .message(e.getMessage())
                    .build();
        }
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public CommonResponse delete (
            @PathVariable Long id
    ) {
        try {
            postsService.delete(id);
            return CommonResponse.builder()
                    .statusCode(StatusCode.SUCCESS)
                    .message("삭제 성공")
                    .build();
        } catch (EntityNotFoundException enfe) {
            return CommonResponse.builder()
                    .statusCode(StatusCode.USER_INFO_NOT_FOUND)
                    .build();
        } catch (Exception e) {
            return CommonResponse.builder()
                    .statusCode(StatusCode.UNKNOWN_EXCEPTION)
                    .message(e.getMessage())
                    .build();
        }
    }
}

