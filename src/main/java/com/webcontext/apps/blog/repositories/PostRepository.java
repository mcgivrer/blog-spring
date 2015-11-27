package com.webcontext.apps.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcontext.apps.blog.model.Post;

/**
 * Repository to store Post.
 * 
 * @author Frédéric Delorme
 *
 */
public interface PostRepository extends JpaRepository<Post, Long> {

}