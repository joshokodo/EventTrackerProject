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
	@GetMapping("games/sort/own/ascend")
	public List<Videogame> allGamesByOwnedAscend(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexByOwnedAscend();
	}
	@GetMapping("games/sort/own/descend")
	public List<Videogame> allGamesByOwnedDescend(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexByOwnedDescend();
	}
	@GetMapping("games/sort/releaseDate/ascend")
	public List<Videogame> allGamesByReleaseDateAscend(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexByReleaseDateAscend();
	}
	@GetMapping("games/sort/releaseDate/descend")
	public List<Videogame> allGamesByReleaseDateDescend(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexByReleaseDateDescend();
	}
	@GetMapping("games/sort/title/ascend")
	public List<Videogame> allGamesByTitleAscend(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexByTitleAscend();
	}
	@GetMapping("games/sort/title/descend")
	public List<Videogame> allGamesByTitleDescend(HttpServletResponse resp, HttpServletRequest req) {
		String newUrl = req.getRequestURL().toString();
		resp.setHeader("Location", newUrl);
		return gameSvc.indexByTitleDescend();
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
	
	

	// helper methods

	// to be used for future refactoring
	private String buildResponseBody(String result, int id, String url) {
		return "{\"result\": \"" + result + "\", \"id\":\"" + id + "\"," + "\"url\"" + url + "\" }";
	}

}
