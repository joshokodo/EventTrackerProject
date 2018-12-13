import { Videogame } from './videogame';

export class Category {
  id: number;
  gameType: string;
  games: Videogame[];

  constructor(id?: number, gameType = 'ACTION', games = []) {
    this.id = id;
    this.gameType = gameType;
    this.games = games;
  }
}
