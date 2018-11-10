package com.skilldistillery.videogame.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Platform {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="system")
	private GameSystem gameSystem;
	
	
	@ManyToMany(mappedBy = "platforms")
	List<Videogame> games;

	
	//--------------------
	// setters and getters
	//--------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GameSystem getGameSystem() {
		return gameSystem;
	}

	public void setGameSystem(GameSystem gameSystem) {
		this.gameSystem = gameSystem;
	}
	
	

	//------------------------
	// hashcode and equals
	//------------------------
	
	public List<Videogame> getGames() {
		return games;
	}

	public void setGames(List<Videogame> games) {
		this.games = games;
	}

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
		Platform other = (Platform) obj;
		if (id != other.id)
			return false;
		return true;
	}

	//-----------
	// toString
	//-----------

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Platform [id=").append(id).append(", gameSystem=").append(gameSystem).append(", games=")
				.append(games).append("]");
		return builder.toString();
	}

	//--------------
	// constructors
	//--------------

	public Platform() {
		super();
	}

	public Platform(GameSystem gameSystem, List<Videogame> games) {
		super();
		this.gameSystem = gameSystem;
		this.games = games;
	}
	
	
	
}
