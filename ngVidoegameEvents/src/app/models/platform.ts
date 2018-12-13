import { Videogame } from './videogame';
export class Platform {
  id: number;
  gameSystem: string;
  games: Videogame[];

  constructor(id?: number, gameSystem?: string, games = [] ) {
    this.id = id;
    this.gameSystem = gameSystem;
    this.games = games;
  }
}
