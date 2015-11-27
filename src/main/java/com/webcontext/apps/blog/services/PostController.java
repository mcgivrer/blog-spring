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

import com.webcontext.apps.blog.model.Post;
import com.webcontext.apps.blog.repositories.PostRepository;

/**
 * The REst controller for the application.
 * 
 * @author Frédéric Delorme
 *
 */
@RestController
public class PostController {

	@Autowired
	private PostRepository repo;

	/**
	 * retrieve all posts
	 * 
	 * @return
	 */
	@RequestMapping(value="/posts", method = RequestMethod.GET)
	public List<Post> findPosts() {
		return repo.findAll();
	}

	/**
	 * retrieve Post for id.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/posts/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Post findById(@PathVariable("id") Integer id) {
		return repo.findOne(id);

	}

	/**
	 * Create a new Post
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(value="/posts", method = RequestMethod.POST)
	public Post addItem(@RequestBody Post item) {
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
	@RequestMapping(value="/posts/{id}", method = RequestMethod.PUT)
	public Post updateItem(@RequestBody Post updatedItem, @PathVariable Integer id) {
		updatedItem.setId(id);
		return repo.saveAndFlush(updatedItem);
	}

	/**
	 * Delete the Post with id.
	 * 
	 * @param id
	 */
	@RequestMapping(value="/posts/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteItem(@PathVariable Integer id) {
		repo.delete(id);
	}
}