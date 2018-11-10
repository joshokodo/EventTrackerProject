package com.skilldistillery.videogame.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Videogame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String description;
	private String features;
	private double price;
	private boolean own;
	
	@Column(name = "release_date")
	private Date releaseDate;
	
	@Enumerated(EnumType.STRING)
	private Rating rating;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToMany(cascade= {CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinTable(name="platform_videogame",
		joinColumns=@JoinColumn(name="videogame_id"),
		inverseJoinColumns=@JoinColumn(name="platform_id")
	)
	private List<Platform> platforms;
	
	
	//----------------------
	// setters and getters
	//----------------------
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public List<Platform> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}
	
	
	
	public boolean getOwn() {
		return own;
	}
	public void setOwn(boolean own) {
		this.own = own;
	}
	//-----------------------
	// hashcode and equals
	//-----------------------
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
		Videogame other = (Videogame) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	//----------
	// toString
	//----------
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Videogame [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", features=").append(features).append(", price=").append(price)
				.append(", own=").append(own).append(", releaseDate=").append(releaseDate).append(", rating=")
				.append(rating).append(", category=").append(category).append(", platforms=").append(platforms)
				.append("]");
		return builder.toString();
	}
	
	
	//----------------
	//  constructors
	//----------------
	public Videogame() {
		super();
	}
	public Videogame(String title, String description, String features, Date releaseDate, Rating rating,
			Category category, double price, boolean own, List<Platform> platforms) {
		super();
		this.title = title;
		this.description = description;
		this.features = features;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.category = category;
		this.price = price;
		this.platforms = platforms;
		this.own = own;
	}
	
	
	
}
