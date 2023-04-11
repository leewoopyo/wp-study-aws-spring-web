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
        String title = "제목1";
        String content = "내용1";
        String author = "글쓴이1";
        PostsDto.SaveRequest saveRequest = PostsDto.SaveRequest.builder()
                                                    .title(title)
                                                    .content(content)
                                                    .author(author)
                                                    .build();

        String url = "http://localhost:" + port + "/api/v1/posts";
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
        assertThat(resultPosts.getContent()).isEqualTo(content);
        assertThat(resultPosts.getAuthor()).isEqualTo(author);
    }

    @Test
    public void postsUpdate () {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                                                .title("제목_1")
                                                .content("내용_1")
                                                .author("글쓴이_1")
                                                .build());
        Long updateId = savedPosts.getId();
        String expectedTitle = "제목_2";
        String expectedContent = "내용_2";
        String expectedAuthor = "글쓴이_2";
        PostsDto.UpdateRequest udpateRequest = PostsDto.UpdateRequest.builder()
                                                                        .title(expectedTitle)
                                                                        .content(expectedContent)
                                                                        .author(expectedAuthor)
                                                                        .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
        HttpEntity<PostsDto.UpdateRequest> requestEntity = new HttpEntity<>(udpateRequest, new HttpHeaders());
        ParameterizedTypeReference<CommonResponse<PostsDto.UpdateRequest>> responseType = new ParameterizedTypeReference<CommonResponse<PostsDto.UpdateRequest>>() {};

        //when
        ResponseEntity<CommonResponse<PostsDto.UpdateRequest>> responseEntity = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                responseType
        );

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getStatusCode()).isEqualTo(StatusCode.SUCCESS);

        Posts resultPosts = postsRepository.findAll().stream()
                .max(Comparator.comparing(Posts::getId))
                .orElse(null);

        assertThat(resultPosts.getTitle()).isEqualTo(expectedTitle);
        assertThat(resultPosts.getContent()).isEqualTo(expectedContent);
        assertThat(resultPosts.getAuthor()).isEqualTo(expectedAuthor);
    }
}
