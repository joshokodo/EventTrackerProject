package com.skilldistillery.videogame.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.videogame.entities.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {

	List<Videogame> findByOwn(boolean own);
	List<Videogame> findByOwnOrderByReleaseDate(boolean own);
	List<Videogame> findByOwnOrderByRating(boolean own);
	List<Videogame> findByOwnOrderByTitle(boolean own);
	List<Videogame> findByOwnOrderByPrice(boolean own);
	List<Videogame> findByOwnOrderByCategory(boolean own);
	
	
}
