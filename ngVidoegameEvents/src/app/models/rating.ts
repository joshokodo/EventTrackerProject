import { Videogame } from './videogame';

export class Rating {
  id: number;
  rated: string;
  games: Videogame[];

  constructor(id?: number, rated = 'TBD', games = []) {
    this.id = id;
    this.rated = rated;
    this.games = games;
  }
}
