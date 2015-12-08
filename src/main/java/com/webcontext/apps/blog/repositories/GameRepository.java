package com.webcontext.apps.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webcontext.apps.blog.model.Game;

/**
 * Repository to store Post.
 * 
 * @author Frédéric Delorme
 *
 */
public interface GameRepository extends JpaRepository<Game, Integer> {

}