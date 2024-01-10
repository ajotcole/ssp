import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GoalboardComponent } from './views/goalboard/goalboard.component';
import { GameComponent } from './views/game/game.component';
import { PlayersComponent } from './views/players/players.component';

export const routes: Routes = [
  { path: 'goalboard', title: 'Goalboard', component: GoalboardComponent },
  { path: 'game', title: 'Game', component: GameComponent },
  { path: 'players', title: 'Players', component: PlayersComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
