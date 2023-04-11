package com.woopi.study.wpstudyawsspringweb.api.posts.service;

import com.woopi.study.wpstudyawsspringweb.api.posts.dto.PostsDto;
import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import com.woopi.study.wpstudyawsspringweb.api.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    /**
     * Posts 정보 저장
     * @param saveRequest
     * @return
     */
    @Transactional
    public Posts save(PostsDto.SaveRequest saveRequest) {
        return postsRepository.save(saveRequest.toEntity());
    }

    /**
     * Posts 정보 수정
     * @param id
     * @param updateRequest
     * @return
     * @throws Exception
     */
    @Transactional
    public Posts update (Long id, PostsDto.UpdateRequest updateRequest) throws Exception {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        posts.update(updateRequest.getTitle(), updateRequest.getContent(), updateRequest.getAuthor());
        postsRepository.flush();

        return posts;
    }

    /**
     * Posts 정보 조회
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public PostsDto.GetResponse findById (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return PostsDto.GetResponse.toDto(posts);
    }
}
