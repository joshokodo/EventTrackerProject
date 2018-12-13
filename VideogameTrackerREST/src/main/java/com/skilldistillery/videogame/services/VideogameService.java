package com.skilldistillery.videogame.services;

import java.util.List;

import com.skilldistillery.videogame.entities.Videogame;

public interface VideogameService {
	
	List<Videogame> index();
	Videogame show(int id);
	Videogame create(Videogame game);
	Videogame update(int id, Videogame game);
	boolean delete(int id);

}
