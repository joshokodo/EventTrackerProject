package com.skilldistillery.videogame.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.videogame.entities.Category;
import com.skilldistillery.videogame.entities.Platform;
import com.skilldistillery.videogame.entities.Rating;
import com.skilldistillery.videogame.entities.Videogame;
import com.skilldistillery.videogame.repositories.CategoryRepository;
import com.skilldistillery.videogame.repositories.PlatformRepository;
import com.skilldistillery.videogame.repositories.RatingRepository;
import com.skilldistillery.videogame.repositories.VideogameRepository;

@Service
public class VideogameServiceImpl implements VideogameService {

	@Autowired
	VideogameRepository gameRepo;

	@Autowired
	RatingRepository ratingRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	PlatformRepository platformRepo;

	@Override
	public List<Videogame> index() {
		return gameRepo.findAll();
	}

	@Override
	public Videogame show(int id) {
		Optional<Videogame> opt = gameRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public Videogame create(Videogame game) {

		game.setTitle(game.getTitle() == null ? "TBD" : game.getTitle());
		
		game.setRating(game.getRating() == null ? getDefaultRating() : game.getRating());

		game.setCategory(game.getCategory() == null ? getDefaultCategory() : game.getCategory());

		game.setPlatforms(game.getPlatforms() == null ? getDefaultPlatforms() 
													  : game.getPlatforms());
		return gameRepo.saveAndFlush(game);
	}

	@Override
	public Videogame update(int id, Videogame game) {
		Videogame existing = null;
		Optional<Videogame> opt = gameRepo.findById(id);

		if (opt.isPresent()) {
			existing = opt.get();
			existing.setDescription(game.getDescription());
			existing.setFeatures(game.getFeatures());
			existing.setTitle(game.getTitle() == null ? existing.getTitle() : game.getTitle());
			existing.setOwn(game.getOwn());
			existing.setReleaseDate(game.getReleaseDate());
			existing.setPrice(game.getPrice());
			existing.setRating(game.getRating() == null ? getDefaultRating() : game.getRating());
			existing.setCategory(game.getCategory() == null ? getDefaultCategory() : game.getCategory());
			existing.setPlatforms(game.getPlatforms() == null ? getDefaultPlatforms() 
													          : game.getPlatforms());
			existing = gameRepo.saveAndFlush(existing);

		}
		return existing;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Videogame game = show(id);
		
		if(game != null && gameRepo.existsById(game.getId())) {
			game.setRating(null);
			game.setCategory(null);
			game.setPlatforms(null);
			gameRepo.delete(game);
			deleted = true;
		}
		
		return deleted;
	}

	@Override
	public List<Videogame> indexOwned() {
		return gameRepo.findByOwn(true);
	}

	@Override
	public List<Videogame> indexOwnedByDate() {

		return gameRepo.findByOwnOrderByReleaseDate(true);
	}

	@Override
	public List<Videogame> indexOwnedByRating() {
		return gameRepo.findByOwnOrderByRating(true);
	}

	@Override
	public List<Videogame> indexOwnedByTitle() {
		return gameRepo.findByOwnOrderByTitle(true);
	}

	@Override
	public List<Videogame> indexOwnedByPrice() {
		return gameRepo.findByOwnOrderByPrice(true);
	}

	@Override
	public List<Videogame> indexOwnedByCategory() {
		return gameRepo.findByOwnOrderByCategory(true);
	}

	@Override
	public List<Videogame> indexWishlist() {
		return gameRepo.findByOwn(false);
	}

	@Override
	public List<Videogame> indexWishlistByDate() {
		return gameRepo.findByOwnOrderByReleaseDate(false);
	}

	@Override
	public List<Videogame> indexWishlistByRating() {
		return gameRepo.findByOwnOrderByRating(false);
	}

	@Override
	public List<Videogame> indexWishlistByTitle() {
		return gameRepo.findByOwnOrderByTitle(false);
	}

	@Override
	public List<Videogame> indexWishlistByPrice() {
		return gameRepo.findByOwnOrderByPrice(false);
	}

	@Override
	public List<Videogame> indexWishlistByCategory() {
		return gameRepo.findByOwnOrderByCategory(false);
	}

	// helper methods
	private Rating getDefaultRating() {
		Optional<Rating> opt = ratingRepo.findById(6);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	private Category getDefaultCategory() {
		Optional<Category> opt = categoryRepo.findById(1);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	private List<Platform> getDefaultPlatforms() {
		List<Platform> platforms = null;

		Optional<Platform> opt = platformRepo.findById(1);
		if (opt.isPresent()) {
			platforms.add(opt.get());
		}
		return platforms;
	}

}
