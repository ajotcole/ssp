import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GoalboardComponent } from './views/goalboard/goalboard.component';
import { GameComponent } from './views/game/game.component';

const routes: Routes = [
  { path: 'goalboard', component: GoalboardComponent },
  { path: 'game', component: GameComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
