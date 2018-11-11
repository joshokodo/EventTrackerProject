package com.skilldistillery.videogame.services;

import java.util.List;

import com.skilldistillery.videogame.entities.Videogame;

public interface VideogameService {
	
	List<Videogame> indexOwned();
	List<Videogame> indexOwnedByDate();
	List<Videogame> indexOwnedByRating();
	List<Videogame> indexOwnedByTitle();
	List<Videogame> indexOwnedByPrice();
	List<Videogame> indexOwnedByCategory();
	
	
	List<Videogame> indexWishlist();
	List<Videogame> indexWishlistByDate();
	List<Videogame> indexWishlistByRating();
	List<Videogame> indexWishlistByTitle();
	List<Videogame> indexWishlistByPrice();
	List<Videogame> indexWishlistByCategory();
	
	List<Videogame> index();
	Videogame show(int id);
	Videogame create(Videogame game);
	Videogame update(int id, Videogame game);
	boolean delete(int id);

}
