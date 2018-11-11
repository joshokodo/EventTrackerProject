package com.skilldistillery.videogame.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VideogameTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Videogame game;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("Videogame");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		game = em.find(Videogame.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		game = null;
	}
	
	@Test
	void test_basic_mappings() {
		assertEquals("Red Dead Redemption 2", game.getTitle());
		assertEquals("gang of outlaws looking to score in one final heist", game.getDescription());
		assertEquals("open world, single player, multiplayer coming soon", game.getFeatures());
		assertEquals(60.00, game.getPrice());
		assertEquals(false, game.getOwn());
	}
	
	@Test
	void test_releaseDate_mappings(){
		assertEquals("2018-10-26 00:00:00.0", game.getReleaseDate().toString());
	}
	
	@Test
	void test_rating_mappings(){
		Rating r = new Rating(Rated.M, null);
		r.setId(4);
		assertEquals(r, game.getRating());
	}
	@Test
	void test_category_mappings(){
		assertEquals(1, game.getCategory().getId());
		assertEquals(GameType.ACTION, game.getCategory().getGameType());
	}
	@Test
	void test_platforms_mappings(){
		assertEquals(3, game.getPlatforms().size());
	}
	
	

}
