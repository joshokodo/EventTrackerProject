import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VideogamesComponent } from './videogames/videogames.component';
import { HttpClientModule } from '@angular/common/http';
import { VideogamesService } from './services/videogames.service';
import { NotFoundComponent } from './not-found/not-found.component';
import { VideogameDetailComponent } from './videogame-detail/videogame-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    VideogamesComponent,
    NotFoundComponent,
    VideogameDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    VideogamesService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
