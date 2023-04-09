package com.woopi.study.wpstudyawsspringweb.api.posts.repository;

import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup () {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndFind () {
        //given
        String title = "테스트_제목";
        String author = "테스트_글쓴이";
        String content = "테스트_본문";
        Posts posts = Posts.builder()
                            .title(title)
                            .author(author)
                            .content(content)
                            .build();
        //when
        postsRepository.save(posts);
        Posts resultPosts = postsRepository.findAll().stream()
                                                        .max(Comparator.comparing(Posts::getId))
                                                        .orElse(null);
        //then
        assertThat(resultPosts.getTitle()).isEqualTo(title);
        assertThat(resultPosts.getAuthor()).isEqualTo(author);
        assertThat(resultPosts.getContent()).isEqualTo(content);
    }
}
