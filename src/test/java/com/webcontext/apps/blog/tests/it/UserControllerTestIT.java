/**
 * 
 */
package com.webcontext.apps.blog.tests.it;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.when;

import java.util.Date;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.webcontext.apps.blog.Application;
import com.webcontext.apps.blog.model.User;
import com.webcontext.apps.blog.model.UserProfile;
import com.webcontext.apps.blog.repositories.UserRepository;

/**
 * Integration test for rest service from UserController.
 * 
 * @author Frédéric Delorme
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("local")
@IntegrationTest("server.port:0")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("/datasets/users.xml")
public class UserControllerTestIT {

	RestTemplate template = new TestRestTemplate();

	@Autowired
	UserRepository userRepo;

	@Value("${local.server.port}")
	int port = 0;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		RestAssured.port = port;
	}

	@Test
	public void canFindAllUsers() {

		when().get("/users").then().statusCode(HttpStatus.SC_OK).body("username",
				Matchers.hasItems("admin", "McGivrer"));
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#findById(long)}.
	 */
	@Test
	public void canFindUserById() {
		when().get("/users/{username}", "McGivrer").then().statusCode(HttpStatus.SC_OK).body("firstname",
				Matchers.is("Frédéric"));
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#addItem(com.webcontext.apps.blog.model.Post)}
	 * .
	 */
	@Test
	public void canAddNewUser() {
		User usertoBeAdded = new User("test01", "test01pwd", "test@contact.com", "test01", "test01", new Date(),
				new Date(), "admin", UserProfile.PUBLIC);
		expect().statusCode(HttpStatus.SC_OK).given().body(usertoBeAdded).contentType(ContentType.JSON).post("/users");
	}

	@Test
	public void canNotAddEmptyFieldsUser() {
		User user = new User("", "", "", "", "", null, null, "", UserProfile.PUBLIC);
		expect().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).given().body(user).contentType(ContentType.JSON)
				.post("/users");

	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#updateItem(com.webcontext.apps.blog.model.Post, java.lang.Long)}
	 * .
	 */
	@Test
	public void canUpdateExistingUser() {
		User userTobeUpdate = userRepo.findOne("admin");
		System.out.println(userTobeUpdate);
		userTobeUpdate.setFirstname("Frédéric");
		expect().statusCode(HttpStatus.SC_OK).given().body(userTobeUpdate).contentType(ContentType.JSON)
				.put("/users/{username}", "username");
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#deleteItem(java.lang.Long)}
	 * .
	 */
	@Test
	public void canDeleteUserMcGivrer() {
		when().delete("/users/{username}", "McGivrer").then().statusCode(HttpStatus.SC_NO_CONTENT);
	}

}
