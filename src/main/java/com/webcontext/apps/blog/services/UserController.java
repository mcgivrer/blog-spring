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

import com.webcontext.apps.blog.model.User;
import com.webcontext.apps.blog.repositories.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository repo;

	/**
	 * retrieve all posts
	 * 
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> findUsers() {
		return repo.findAll();
	}

	/**
	 * retrieve Post for id.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User findById( @PathVariable("username" ) String id) {
		return repo.findOne(id);

	}

	/**
	 * Create a new Post
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User addItem( @RequestBody User item ) {
		// item.setusername(null);
		return repo.saveAndFlush(item);
	}

	/**
	 * Update a Post
	 * 
	 * @param updatedItem
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
	public User updateItem( @RequestBody User updatedItem, @PathVariable String username ) {
		updatedItem.setUsername(username);
		return repo.saveAndFlush(updatedItem);
	}

	/**
	 * Delete the Post with id.
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteItem( @PathVariable String username ) {
		repo.delete(username);
	}

}
