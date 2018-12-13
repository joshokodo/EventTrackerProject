package com.skilldistillery.videogame.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.videogame.entities.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {

//	List<Videogame> findByOwn(boolean own);
//	List<Videogame> findByOwnOrderByReleaseDate(boolean own);
//	List<Videogame> findByOwnOrderByRating(boolean own);
//	List<Videogame> findByOwnOrderByTitle(boolean own);
//	List<Videogame> findByOwnOrderByPrice(boolean own);
//	List<Videogame> findByOwnOrderByCategory(boolean own);
//	
//	@Query("SELECT g FROM Videogame g ORDER BY g.own ASC")
//	List<Videogame> sortAllByOwnAsc();
//	@Query("SELECT g FROM Videogame g ORDER BY g.own DESC")
//	List<Videogame> sortAllByOwnDesc();
//	
//	@Query("SELECT g FROM Videogame g ORDER BY g.releaseDate ASC")
//	List<Videogame> sortAllByReleaseDateAsc();
//	@Query("SELECT g FROM Videogame g ORDER BY g.releaseDate DESC")
//	List<Videogame> sortAllByReleaseDateDesc();
//	
//	
//	@Query("SELECT g FROM Videogame g ORDER BY g.title ASC")
//	List<Videogame> sortAllByTitleAsc();
//	@Query("SELECT g FROM Videogame g ORDER BY g.title DESC")
//	List<Videogame> sortAllByTitleDesc();
	
}
