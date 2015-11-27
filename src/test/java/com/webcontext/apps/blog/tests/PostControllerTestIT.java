/**
 * 
 */
package com.webcontext.apps.blog.tests;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.when;

import java.util.Arrays;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.ResponseSpecification;
import com.webcontext.apps.blog.Application;
import com.webcontext.apps.blog.model.Post;
import com.webcontext.apps.blog.model.PublicationState;
import com.webcontext.apps.blog.repositories.PostRepository;

/**
 * @author frederic
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PostControllerTestIT {

	RestTemplate template = new TestRestTemplate();

	@Autowired
	private PostRepository postRepository;

	private Date today;
	private Post post0, post1, post2, post3;

	@Value("${local.server.port}")
	int port = 0;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		today = new Date();
		post0 = new Post("title0", "cover", "header0", "content0", today, "McGivrer", PublicationState.DRAFT);
		post1 = new Post("title1", "cover", "header1", "content1", today, "Math", PublicationState.TOBEVALIDATED);
		post2 = new Post("title2", "cover", "header2", "content2", today, "Soso", PublicationState.VALIDATED);
		post3 = new Post("title3", "cover", "header3", "content3", today, "Nath", PublicationState.PUBLISHED);
		postRepository.save(Arrays.asList(post0, post1, post2, post3));

		RestAssured.port = port;
	}

	@Test
	public void canFindAllPost() {
		when().get("/posts").then().statusCode(HttpStatus.SC_OK).body("title",
				Matchers.hasItems("title0", "title1", "title2", "title3"));
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#findById(long)}.
	 */
	@Test
	public void canFindPostById() {
		Integer id = post0.getId();
		when().get("/posts/{id}", id).then().statusCode(HttpStatus.SC_OK).body("id", Matchers.is(id));
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#addItem(com.webcontext.apps.blog.model.Post)}
	 * .
	 */
	@Test
	public void canAddNewPost() {
		Post addedPost = new Post("title", "cover", "header", "content", new Date(), "McG", PublicationState.DRAFT);
		expect().statusCode(HttpStatus.SC_OK).given().body(addedPost).contentType(ContentType.JSON).post("/posts");
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#updateItem(com.webcontext.apps.blog.model.Post, java.lang.Long)}
	 * .
	 */
	@Test
	public void canUpdateExistingPost() {
		post1.setContent("contentmodified");
		expect().statusCode(HttpStatus.SC_OK).given().body(post1).contentType(ContentType.JSON).put("/posts/{id}",
				post1.getId());
	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#deleteItem(java.lang.Long)}
	 * .
	 */
	@Test
	public void canDeletePost0() {
		Integer postId = post0.getId();

		when().delete("/posts/{id}", postId).then().statusCode(HttpStatus.SC_NO_CONTENT);
	}

}
