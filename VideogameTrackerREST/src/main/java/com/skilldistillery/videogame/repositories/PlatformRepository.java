 package com.skilldistillery.videogame.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.videogame.entities.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Integer> {

}
