package com.webcontext.apps.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcontext.apps.blog.model.Post;

public interface IPostRepository  extends JpaRepository<Post, Long> {

}