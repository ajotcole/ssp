import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ScoreboardComponent } from './views/scoreboard/scoreboard.component';
import { GameComponent } from './views/game/game.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PlayersComponent } from './views/players/players.component';
import { HttpClientModule } from '@angular/common/http';
import { GraphQLModule } from './graphql.module';
import { ToastService } from './services/toast.service';

@NgModule({
  declarations: [
    AppComponent,
    ScoreboardComponent,
    GameComponent,
    NavbarComponent,
    PlayersComponent,
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    HttpClientModule,
    GraphQLModule,
  ],
  providers: [ToastService],
  bootstrap: [AppComponent],
})
export class AppModule {}
