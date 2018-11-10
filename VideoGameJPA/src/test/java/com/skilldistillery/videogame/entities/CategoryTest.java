package com.skilldistillery.videogame.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Category category;

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
		category = em.find(Category.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		category = null;
	}
	@Test
	void test_gametype_name_mapping() {
		assertEquals(GameType.ACTION, category.getGameType());
	}
	@Test
	void test_games_mapping() {
		assertEquals(1, category.getGames().size());
	}

}
