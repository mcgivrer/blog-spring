/**
 * file: PostRepositoryTest.java
 * date: 30 nov. 2015
 *
 * GEHC DoseWatch
 *
 * Copyright (c) 2015 by General Electric Company. All rights reserved.
 * 
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 *
 */

package com.webcontext.apps.blog.tests.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.webcontext.apps.blog.Application;
import com.webcontext.apps.blog.model.Post;
import com.webcontext.apps.blog.model.PublicationState;
import com.webcontext.apps.blog.repositories.PostRepository;

/**
 * Unit Tests for PostRepository.
 * 
 * @author Frédéric Delorme
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("/datasets/posts.xml")
public class PostRepositoryTest {

	@Autowired
	PostRepository postRepo;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	public void testSaveS() {
		Date today = new Date();
		Post post = new Post("Mario maker", "http://ecx.images-amazon.com/images/I/81NvDQon5NL._SL1500_.jpg",
				"Avec un nombre quasiment illimité de stages, Super Mario Maker exclusivement disponible sur Wii U "
						+ "propose une expérience Mario amusante destinées à tous et sans limite ! ",
				"Jouez : un jeu Mario qui ne se termine jamais !"
						+ "En vous connectant à Internet*, vous pouvez télécharger et jouer dans des stages créés par d'autres joueurs du monde entier ! "
						+ "100 stages sont également inclus et seront jouables sans qu'une connexion à Internet soit nécessaire. "
						+ "Créez : concevez vos propres stages ! "
						+ "Donnez libre cours à votre imagination et découvrez ce que vous arriverez à réaliser avec un éditeur de stages intuitif. "
						+ "Partagez : envoyez vos créations pour que le monde entier en profite ! "
						+ "En publiant vos propres créations, vous donnez aux joueur du monde entier la possibilité de les essayer ! "
						+ "Découvrez d'innombrables stages aussi surprenants que nombreux, et éditez les créations d'autres joueurs "
						+ "pour ajouter vos propres idées et créer quelque chose de nouveau et d'unique !",
				today, "McG", new Locale("fr", "FR"), "+7", PublicationState.DRAFT);
		post = postRepo.save(post);
		assertNotNull(post.getId());
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)}
	 * .
	 */
	@Test
	public void testFindOne() {
		Post post = postRepo.findOne(new Integer(1));
		assertNotNull(post);
		assertEquals("Watch Dogs", post.getTitle());
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Post> posts = postRepo.findAll();
		List<String> titles = Arrays.asList("Watch Dogs", "The bureau XCOM declassified", "The Witcher 3");
		for (Post post : posts) {
			assertEquals(true, titles.contains(post.getTitle()));
		}
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#count()}.
	 */
	@Test
	public void testCount() {
		long count = postRepo.count();
		assertEquals(3, count);
	}

	/**
	 * Test method for
	 * {@link org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)}
	 * .
	 */
	@Test
	public void testDeleteID() {
		postRepo.delete(1);
		Post post = postRepo.findOne(1);
		assertEquals(null, post);
	}

}
