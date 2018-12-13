import { Videogame } from '../models/videogame';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class VideogamesService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/games';

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Videogame[]>(this.url + '?sorted=true').pipe(
      catchError(this.handleError));
  }

  show(id: string) {
    return this.http.get<Videogame>(this.url + '/' + id).pipe(
      catchError(this.handleError));
  }

  create(data: Videogame) {
    return this.http.post<Videogame>(this.url, data).pipe(
      catchError(this.handleError));
  }

  destroy(id: number) {
    return this.http.delete<Videogame>( this.url + '/' + id).pipe(
      catchError(this.handleError));
  }

  update(id: number, data: Videogame) {

    // if (data.completed) {
    //   data.completeDate = this.datePipe.transform(Date.now(), 'shortDate');
    // } else {
    //   data.completeDate = '';
    // }
    return this.http.put<Videogame>(this.url + '/' + id, data).pipe(
      catchError(this.handleError));
  }

  handleError(error: any) {
    console.error('Something Broke');
    return throwError(error.json().error || 'Server Error');
  }
}
