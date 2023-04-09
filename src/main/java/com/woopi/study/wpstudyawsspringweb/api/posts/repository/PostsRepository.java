package com.woopi.study.wpstudyawsspringweb.api.posts.repository;

import com.woopi.study.wpstudyawsspringweb.api.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
