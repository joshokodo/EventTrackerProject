package com.skilldistillery.videogame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.videogame.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
