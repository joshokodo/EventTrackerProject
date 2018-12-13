import { Rating } from './rating';
import { Category } from './category';
import { Platform } from './platform';

export class Videogame {
  id: number;
  title: string;
  description: string;
  features: string;
  price: number;
  own: boolean;
  releaseDate: number;
  rating: Rating;
  category: Category;
  platforms: Platform[];

  constructor(
    id?: number,
    title = 'N/A',
    description?: string,
    features?: string,
    price = 0,
    own = false,
    releaseDate = Date.now(),
    rating = new Rating(3, 'T' ),
    category = new Category(1, 'ACTION'),
    platforms?: Platform[]) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.features = features;
    this.price = price;
    this.own = own;
    this.releaseDate = releaseDate;
    this.rating = rating;
    this.category = category;
    this.platforms = platforms;
  }
}
