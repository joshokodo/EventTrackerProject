import { Component, OnInit } from '@angular/core';
import { Videogame } from '../models/videogame';
import { VideogamesService } from '../services/videogames.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Platform } from '../models/platform';
import { PlatformBuilder } from '../models/platform-builder';

@Component({
  selector: 'app-videogame-detail',
  templateUrl: './videogame-detail.component.html',
  styleUrls: ['./videogame-detail.component.css']
})
export class VideogameDetailComponent implements OnInit {
  selected: Videogame = null;
  editGame: Videogame = null;
  platformBuilder: PlatformBuilder = new PlatformBuilder();

  constructor(
    private gameService: VideogamesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const gameId = this.route.snapshot.paramMap.get('id');

    if (gameId) {
      this.showGame(gameId);
    } else {
      console.log('going back to games from detail component');
      this.goToGamesListPage();
    }
  }
  goToGamesListPage() {
    this.selected = null;
    this.router.navigateByUrl('games');
  }

  showGame(id: string) {
    this.gameService.show(id).subscribe(
      data => {
        if (data) {
          this.selected = data;
        } else {
          this.router.navigateByUrl('notFound');
        }
      },

      err => console.error('Observer got an error: ' + err)
    );
  }
  updateGame(game: Videogame) {
    this.platformBuilder.setPlatforms(game);
    this.gameService.update(game.id, game).subscribe(
      data => {
        this.editGame = null;
        this.selected = data;
      },

      err => console.error('Observer got an error: ' + err)
      );
  }

  deleteGame(id: number) {
    this.gameService.destroy(id).subscribe(
      data => {
        this.selected = null;
        this.editGame = null;
        this.platformBuilder.platforms = [false, false, false, false, false];
        this.goToGamesListPage();
      },
      err => console.error('Observer got an error: ' + err)
      );
  }

  getFullRating(rating: string): string {
    let gameRating = '';
    if (rating === 'E') {
      gameRating = 'Everyone(E)';

    } else if (rating === 'E10UP') {
      gameRating = 'Everyone 10+(E10+)';

    } else if (rating === 'T') {
      gameRating = 'Teen(T)';

    } else if (rating === 'M') {
      gameRating = 'Mature(M)';

    } else if (rating === 'A') {
      gameRating = 'Adult(A)';

    } else {
      gameRating = 'TBD';

    }
    return gameRating;
  }

  setEditGame() {
    this.editGame = this.selected;
    this.platformBuilder.hasGameSystem(this.editGame);
    this.selected = null;
  }
  resetEditGame() {
    this.selected = this.editGame;
    this.platformBuilder.platforms = [false, false, false, false, false];
    this.editGame = null;
  }

}
