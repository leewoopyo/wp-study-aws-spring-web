package com.woopi.study.wpstudyawsspringweb.api.posts.controller;

import com.woopi.study.wpstudyawsspringweb.api.common.dto.CommonResponse;
import com.woopi.study.wpstudyawsspringweb.api.posts.dto.PostsDto;
import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import com.woopi.study.wpstudyawsspringweb.api.posts.repository.PostsRepository;
import com.woopi.study.wpstudyawsspringweb.enumeration.StatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void cleanup () {
        postsRepository.deleteAll();
    }

    @Test
    public void postsSave () {
        //given
        String url = "http://localhost:" + port + "/api/v1/posts";
        String title = "제목1";
        String content = "내용1";
        String author = "글쓴이1";
        PostsDto.SaveRequest saveRequest = PostsDto.SaveRequest.builder()
                                                    .title(title)
                                                    .content(content)
                                                    .author(author)
                                                    .build();
        HttpEntity<PostsDto.SaveRequest> requestEntity = new HttpEntity<>(saveRequest, new HttpHeaders());
        ParameterizedTypeReference<CommonResponse<PostsDto.SaveResponse>> responseType = new ParameterizedTypeReference<CommonResponse<PostsDto.SaveResponse>>() {};
        //when
        ResponseEntity<CommonResponse<PostsDto.SaveResponse>> responseEntity = testRestTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getStatusCode()).isEqualTo(StatusCode.SUCCESS);
        assertThat(responseEntity.getBody().getData().getTitle()).isEqualTo(title);
        assertThat(responseEntity.getBody().getData().getContent()).isEqualTo(content);
        assertThat(responseEntity.getBody().getData().getAuthor()).isEqualTo(author);

        Posts resultPosts = postsRepository.findAll().stream()
                .max(Comparator.comparing(Posts::getId))
                .orElse(null);

        assertThat(resultPosts.getTitle()).isEqualTo(title);
        assertThat(resultPosts.getAuthor()).isEqualTo(author);
        assertThat(resultPosts.getContent()).isEqualTo(content);
    }
}
