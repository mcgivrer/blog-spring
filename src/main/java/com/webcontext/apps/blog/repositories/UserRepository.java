package com.webcontext.apps.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcontext.apps.blog.model.User;

/**
 * Repository to store Post.
 * 
 * @author Frédéric Delorme
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

}