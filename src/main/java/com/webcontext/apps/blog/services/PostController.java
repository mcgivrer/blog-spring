package com.webcontext.apps.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webcontext.apps.blog.model.Post;
import com.webcontext.apps.blog.repositories.IPostRepository;

/**
 * The REst controller for the application.
 * 
 * @author Frédéric Delorme
 *
 */
@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private IPostRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Post> findPosts() {
		return repo.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Post addItem(@RequestBody Post item) {
		item.setId(null);
		return repo.saveAndFlush(item);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Post updateItem(@RequestBody Post updatedItem, @PathVariable Long id) {
		updatedItem.setId(id);
		return repo.saveAndFlush(updatedItem);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable Long id) {
		repo.delete(id);
	}
}