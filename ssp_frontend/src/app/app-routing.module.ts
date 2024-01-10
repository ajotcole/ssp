import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ScoreboardComponent } from './views/scoreboard/scoreboard.component';
import { GameComponent } from './views/game/game.component';
import { PlayersComponent } from './views/players/players.component';

export const routes: Routes = [
  { path: 'scoreboard', title: 'Scoreboard', component: ScoreboardComponent },
  { path: 'game', title: 'Game', component: GameComponent },
  { path: 'players', title: 'Players', component: PlayersComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
