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
		assertEquals("Outerspace", game.getTitle());
		assertEquals("Partake in space battles in space", game.getDescription());
		assertEquals("Online multiplayer, single player campaign, up to 4 players on one screen", game.getFeatures());
		assertEquals(60.00, game.getPrice());
	}
	
	@Test
	void test_releaseDate_mappings(){
		assertEquals("2018-11-11 00:00:00.0", game.getReleaseDate().toString());
	}
	
	@Test
	void test_rating_mappings(){
		assertEquals(Rating.M, game.getRating());
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
