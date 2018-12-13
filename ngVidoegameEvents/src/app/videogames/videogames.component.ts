import { PlatformBuilder } from './../models/platform-builder';
import { VideogamesService } from './../services/videogames.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Videogame } from '../models/videogame';
import { NgForm } from '@angular/forms';
import { Platform } from '../models/platform';

@Component({
  selector: 'app-videogames',
  templateUrl: './videogames.component.html',
  styleUrls: ['./videogames.component.css']
})
export class VideogamesComponent implements OnInit {

  newGame: Videogame = null;
  games: Videogame[] = [];
  addingGame = false;
  platformBuilder: PlatformBuilder = new PlatformBuilder();


  constructor(private gameService: VideogamesService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.loadGames();
  }

  loadGames(): any {
    this.addingGame = false;
    this.gameService.index().subscribe(
      data => this.games = data,

      err => console.error('Observer got an error: ' + err)
    );
  }
  goToDetailPage(id: string) {
    this.router.navigateByUrl('games/' + id);
  }

  deleteGame(id: number) {
    this.gameService.destroy(id).subscribe(
      data => {
        this.loadGames();
      },
      err => console.error('Observer got an error: ' + err)
      );
  }



    addGame(game: Videogame) {
      this.platformBuilder.setPlatforms(this.newGame);
      this.gameService.create(this.newGame).subscribe(
        data => {
          this.loadGames();
          this.newGame = null;
        },

        err => console.error('Observer got an error: ' + err)
        );
      }

      toggleOwned(game: Videogame) {
        game.own = !game.own;
        this.gameService.update(game.id, game).subscribe(
          data => {
            this.loadGames();
          },

          err => console.error('Observer got an error: ' + err)
          );
      }

      readyToAdd() {
        this.addingGame = true;
        this.newGame = new Videogame();
      }

      gameAvailableNow(game: Videogame) {
        const releaseDate = new Date(game.releaseDate).getTime();
        return releaseDate < new Date().getTime();
      }

      resetNewGame() {
        this.newGame = null;
        this.addingGame = false;
      }

      getGamesOwnedRatio() {
        let count = 0;
        this.games.forEach(element => {
          if (element.own) {
            count++;
          }
        });

        return count + ' out of ' + this.games.length;
      }

      getTotalCost() {
        let price = 0;
        this.games.forEach(element => {
          if (!element.own) {
            price += element.price;
          }
        });
        return price;
      }

}
