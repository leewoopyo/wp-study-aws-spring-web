package com.woopi.study.wpstudyawsspringweb.api.posts.service;

import com.woopi.study.wpstudyawsspringweb.api.posts.dto.PostsDto;
import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import com.woopi.study.wpstudyawsspringweb.api.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Posts save(PostsDto.SaveRequest saveRequest) {
        return postsRepository.save(saveRequest.toEntity());
    }
}
