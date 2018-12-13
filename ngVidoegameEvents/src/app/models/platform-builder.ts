import { Videogame } from './videogame';
import { Platform } from './platform';

export class PlatformBuilder {
  platforms: boolean[] = [false, false, false, false, false];

  hasGameSystem(game: Videogame) {
    game.platforms.forEach((element) => {
      if (element.gameSystem === 'PS4') {
        this.platforms[0] = true;

      } else if (element.gameSystem === 'XBOX1') {
        this.platforms[1] = true;

      } else if (element.gameSystem === 'PC') {
        this.platforms[2] = true;

      } else if (element.gameSystem === 'SWITCH') {
        this.platforms[3] = true;

      } else if (element.gameSystem === 'THREE_DS') {
        this.platforms[4] = true;

      }
    });
  }

  setPlatforms(game: Videogame) {
    game.platforms = [];
    if (this.platforms[0]) {
      game.platforms.push(new Platform(1, 'PS4'));
    }
    if (this.platforms[1]) {
      game.platforms.push(new Platform(2, 'XBOX1'));
    }
    if (this.platforms[2]) {
      game.platforms.push(new Platform(3, 'PC'));
    }
    if (this.platforms[3]) {
      game.platforms.push(new Platform(4, 'SWITCH'));
    }
    if (this.platforms[4]) {
      game.platforms.push(new Platform(5, 'THREE_DS'));
    }
  }
}
