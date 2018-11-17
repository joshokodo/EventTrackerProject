package com.skilldistillery.videogame.services;

import java.util.List;

import com.skilldistillery.videogame.entities.Videogame;

public interface VideogameService {
	List<Videogame> indexByOwnedAscend();
	List<Videogame> indexByOwnedDescend();
	List<Videogame> indexByReleaseDateAscend();
	List<Videogame> indexByReleaseDateDescend();
	List<Videogame> indexByTitleAscend();
	List<Videogame> indexByTitleDescend();
	
	List<Videogame> index();
	Videogame show(int id);
	Videogame create(Videogame game);
	Videogame update(int id, Videogame game);
	boolean delete(int id);

}
