package com.woopi.study.wpstudyawsspringweb.api.posts.service;

import com.woopi.study.wpstudyawsspringweb.api.posts.dto.PostsDto;
import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import com.woopi.study.wpstudyawsspringweb.api.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Posts 전체 조회 (Sort id Desc)
     */
    @Transactional(readOnly = true)
    public List<PostsDto.GetResponse> findAllByDesc () {
        List<Posts> postsList = postsRepository.findAllDesc();
        if (postsList.isEmpty() || postsList.size() <= 0) {
            return null;
        }

        List<PostsDto.GetResponse> postsDtoList = postsList.stream()
                                                            .map(PostsDto.GetResponse::toDto)
                                                            .collect(Collectors.toList());
        return postsDtoList;
    }

    /**
     * Posts 삭제
     */
    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        postsRepository.delete(posts);
    }
}
