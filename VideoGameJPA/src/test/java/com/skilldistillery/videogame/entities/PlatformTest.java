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

class PlatformTest {

	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Platform platform;

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
		platform = em.find(Platform.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		platform = null;
	}
	@Test
	void test_gametype_name_mapping() {
		assertEquals(GameSystem.PS4, platform.getGameSystem());
	}
	@Test
	void test_games_mapping() {
		assertEquals(1, platform.getGames().size());
	}

}
