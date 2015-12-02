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
import com.webcontext.apps.blog.model.User;
import com.webcontext.apps.blog.model.UserProfile;
import com.webcontext.apps.blog.repositories.UserRepository;

/**
 * Unit Tests for UserRespository.
 * 
 * @author Frédéric Delorme
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("/datasets/users.xml")
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepo;

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
	 * Test method for {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	public void testSaveS() {
		Date today = new Date();
		User usertoBeAdded = new User("test01", "test01pwd", "test@contact.com", "test01", "test01", today, today,
				"admin", UserProfile.PUBLIC);
		usertoBeAdded = userRepo.save(usertoBeAdded);
		assertNotNull(usertoBeAdded.getUsername());
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)}.
	 */
	@Test
	public void testFindOne() {
		User user = userRepo.findOne("admin");
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<User> users = userRepo.findAll();
		List<String> usernames = Arrays.asList("admin", "McGivrer");
		for (User user : users) {
			assertEquals(true, usernames.contains(user.getUsername()));
		}
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#count()}.
	 */
	@Test
	public void testCount() {
		long count = userRepo.count();
		assertEquals(2, count);
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#delete(java.io.Serializable)}.
	 */
	@Test
	public void testDeleteID() {
		userRepo.delete("McGivrer");
		User user = userRepo.findOne("McGivrer");
		assertEquals(null, user);
	}

}
