package com.skilldistillery.videogame.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private Rated rated;
	
	@JsonIgnore
	@OneToMany(mappedBy="rating", cascade= {CascadeType.REMOVE, CascadeType.MERGE})
	private List<Videogame> games;

	//---------------------
	// setters and getters
	//---------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rated getRated() {
		return rated;
	}

	public void setRated(Rated rated) {
		this.rated = rated;
	}

	public List<Videogame> getGames() {
		return games;
	}

	public void setGames(List<Videogame> games) {
		this.games = games;
	}

	//----------------
	// add and remove
	//----------------
	
	public void addGame(Videogame game) {
		if (games == null)
			games = new ArrayList<>();
		if (!games.contains(game)) {
			games.add(game);
			game.setRating(this);
		}
	}

	public void removeGame(Videogame game) {
		if (games != null && games.contains(game)) {
			games.remove(game);
			game.setRating(null);;
		}
	}
	
	
	//--------------------
	// hashcode and equals
	//--------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	//---------------
	// toString
	//---------------

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rating [id=").append(id).append(", rated=").append(rated).append(", games=").append(games)
				.append("]");
		return builder.toString();
	}

	//---------------
	// constructors
	//---------------

	public Rating(Rated rated, List<Videogame> games) {
		super();
		this.rated = rated;
		this.games = games;
	}

	public Rating() {
		super();
	}
	
	
	
	

}

