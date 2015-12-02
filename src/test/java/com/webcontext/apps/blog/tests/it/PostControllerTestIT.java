/**
 * 
 */
package com.webcontext.apps.blog.tests.it;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.when;

import java.util.Date;
import java.util.Locale;

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
import com.webcontext.apps.blog.model.Post;
import com.webcontext.apps.blog.model.PublicationState;
import com.webcontext.apps.blog.repositories.PostRepository;

/**

 * Integration test for rest service from PostController.
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
@DatabaseSetup("/datasets/posts.xml")
public class PostControllerTestIT {

	RestTemplate template = new TestRestTemplate();

	@Autowired
	PostRepository postRepo;

	private Date today;

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
	public void canFindAllPost() {
		when().get("/posts").then().statusCode(HttpStatus.SC_OK).body("title",
				Matchers.hasItems("Watch Dogs", "The bureau XCOM declassified", "The Witcher 3"));
	}

	/**
	 * Test method for {@link com.webcontext.apps.blog.services.PostController#findById(long)}.
	 */
	@Test
	public void canFindPostById() {
		Integer id = 1;
		when().get("/posts/{id}", id).then().statusCode(HttpStatus.SC_OK).body("title", Matchers.is("Watch Dogs"));
	}

	/**
	 * Test method for {@link com.webcontext.apps.blog.services.PostController#addItem(com.webcontext.apps.blog.model.Post)} .
	 */
	@Test
	public void canAddNewPost() {
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
		expect().statusCode(HttpStatus.SC_OK).given().body(post).contentType(ContentType.JSON).post("/posts");
	}

	@Test
	public void canNotAddEmptyFieldsPost() {
		Post post = new Post("", "", "", "", today, "", new Locale("fr", "FR"), "+7", PublicationState.DRAFT);
		expect().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).given().body(post).contentType(ContentType.JSON)
				.post("/posts");

	}

	/**
	 * Test method for
	 * {@link com.webcontext.apps.blog.services.PostController#updateItem(com.webcontext.apps.blog.model.Post, java.lang.Long)} .
	 */
	@Test
	public void canUpdateExistingPost() {
		Post postTobeUpdate = postRepo.findOne(2);
		System.out.println(postTobeUpdate);
		postTobeUpdate.setContent("This is a new value");
		expect().statusCode(HttpStatus.SC_OK).given().body(postTobeUpdate).contentType(ContentType.JSON)
				.put("/posts/{id}", 2);
	}

	/**
	 * Test method for {@link com.webcontext.apps.blog.services.PostController#deleteItem(java.lang.Long)} .
	 */
	@Test
	public void canDeletePost0() {
		Integer postId = 1;

		when().delete("/posts/{id}", postId).then().statusCode(HttpStatus.SC_NO_CONTENT);
	}

}
