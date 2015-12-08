package com.webcontext.apps.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webcontext.apps.blog.model.Game;
import com.webcontext.apps.blog.repositories.GameRepository;

/**
 * The REst controller for the application.
 * 
 * @author Frédéric Delorme
 *
 */
@RestController
public class GameController {

	@Autowired
	private GameRepository repo;

	/**
	 * retrieve all posts
	 * 
	 * @return
	 */
	@RequestMapping(value="/games", method = RequestMethod.GET)
	public List<Game> findPosts() {
		return repo.findAll();
	}

	/**
	 * retrieve Post for id.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/game/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Game findById(@PathVariable("id") Integer id) {
		return repo.findOne(id);

	}

	/**
	 * Create a new Post
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(value="/game", method = RequestMethod.POST)
	public Game addItem(@RequestBody Game item) {
		item.setId(null);
		return repo.saveAndFlush(item);
	}

	/**
	 * Update a Post
	 * 
	 * @param updatedItem
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/game/{id}", method = RequestMethod.PUT)
	public Game updateItem(@RequestBody Game updatedItem, @PathVariable Integer id) {
		updatedItem.setId(id);
		return repo.saveAndFlush(updatedItem);
	}

	/**
	 * Delete the Post with id.
	 * 
	 * @param id
	 */
	@RequestMapping(value="/game/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteItem(@PathVariable Integer id) {
		repo.delete(id);
	}
}