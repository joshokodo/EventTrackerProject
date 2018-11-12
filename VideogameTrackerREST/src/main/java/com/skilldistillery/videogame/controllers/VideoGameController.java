package com.skilldistillery.videogame.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.videogame.entities.Videogame;
import com.skilldistillery.videogame.services.VideogameService;

@RestController
@RequestMapping("api")
public class VideoGameController {

	@Autowired
	VideogameService gameSvc;

	@GetMapping("games")
	public List<Videogame> allGames(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.index();
	}

	@GetMapping("games/{id}")
	public Videogame gameById(@PathVariable int id, HttpServletResponse resp, HttpServletRequest req) {

		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.show(id);
	}

	@PostMapping("games")
	public Videogame addNewGame(@RequestBody Videogame game, HttpServletResponse resp, HttpServletRequest req) {

		String newUrl = "";
		game = gameSvc.create(game);
		if (game == null) {
			resp.setStatus(404);
			newUrl = req.getRequestURL().toString();
		} else {
			resp.setStatus(201);
			newUrl = req.getRequestURL().toString() + "/" + (game.getId());
		}
		resp.setHeader("Location", newUrl);
		return game;
	}

	@PutMapping("games/{id}")
	public Videogame updateGame(@PathVariable int id, @RequestBody Videogame game, HttpServletResponse resp,
			HttpServletRequest req) {
		String newUrl = "";
		game = gameSvc.update(id, game);
		if (game == null) {
			resp.setStatus(404);
			newUrl = req.getRequestURL().toString();
		} else {
			resp.setStatus(200);
			newUrl = req.getRequestURL().toString() + "/" + (game.getId());
		}
		resp.setHeader("Location", newUrl);
		return game;
	}

	@DeleteMapping("games/{id}")
	public boolean deletePost(@PathVariable int id, HttpServletResponse resp, HttpServletRequest req) {
		boolean deleteSuccess = gameSvc.delete(id);
		resp.setStatus(!deleteSuccess ? 404 : 200);
		return deleteSuccess;
	}
	
	// owned games
	@GetMapping("games/own")
	public List<Videogame> allOwnedGames(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexOwned();
	}
	@GetMapping("games/own/sort/date")
	public List<Videogame> allOwnedGamesByDate(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexOwnedByDate();
	}
	@GetMapping("games/own/sort/rating")
	public List<Videogame> allOwnedGamesByRating(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexOwnedByRating();
	}
	@GetMapping("games/own/sort/title")
	public List<Videogame> allOwnedGamesByTitle(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexOwnedByTitle();
	}
	@GetMapping("games/own/sort/price")
	public List<Videogame> allOwnedGamesByPrice(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexOwnedByPrice();
	}
	@GetMapping("games/own/sort/category")
	public List<Videogame> allOwnedGamesByCategory(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexOwnedByCategory();
	}
	

	// not owned games
	@GetMapping("games/wishlist")
	public List<Videogame> allWishlistGames(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexWishlist();
	}
	@GetMapping("games/wishlist/sort/date")
	public List<Videogame> allWishlistGamesByDate(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexWishlistByDate();
	}
	@GetMapping("games/wishlist/sort/rating")
	public List<Videogame> allWishlistGamesByRating(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexWishlistByRating();
	}
	@GetMapping("games/wishlist/sort/title")
	public List<Videogame> allWishlistGamesByTitle(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexWishlistByTitle();
	}
	@GetMapping("games/wishlist/sort/price")
	public List<Videogame> allWishlistGamesByPrice(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexWishlistByPrice();
	}
	@GetMapping("games/wishlist/sort/category")
	public List<Videogame> allWishlistGamesByCategory(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexWishlistByCategory();
	}

	// helper methods

	// to be used for future refactoring
	private String buildResponseBody(String result, int id, String url) {
		return "{\"result\": \"" + result + "\", \"id\":\"" + id + "\"," + "\"url\"" + url + "\" }";
	}

}
