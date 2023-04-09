package com.woopi.study.wpstudyawsspringweb.api.posts.dto;

import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import io.swagger.annotations.ApiParam;
import lombok.*;

public class PostsDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class SaveRequest {
        @ApiParam(value = "제목")
        private String title;
        @ApiParam(value = "내용")
        private String content;
        @ApiParam(value = "글쓴이")
        private String author;

        public Posts toEntity () {
            return Posts.builder()
                    .title(this.title)
                    .content(this.content)
                    .author(this.author)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class SaveResponse {

        @ApiParam(value = "제목")
        private String title;
        @ApiParam(value = "내용")
        private String content;
        @ApiParam(value = "글쓴이")
        private String author;

        public static SaveResponse toDto (Posts posts) {
            return PostsDto.SaveResponse.builder()
                                        .title(posts.getTitle())
                                        .content(posts.getContent())
                                        .author(posts.getAuthor())
                                        .build();
        }
    }
}
