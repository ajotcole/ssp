import { Component } from '@angular/core';
import {
  FindAllGamesGQL,
  FindAllGamesQuery,
} from '../../models/generated/graphql';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-goalboard',
  template: `
    <h3 class="text-center">🎯 Goalboard 🎯</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th sortable="name">Player Name</th>
          <th sortable="rounds">Rounds</th>
          <th sortable="name">Winner</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let game of games | async">
          <td>{{ game?.playerId }}</td>
          <td>{{ game?.rounds }}</td>
          <td>{{ game?.winner }}</td>
        </tr>
      </tbody>
    </table>
  `,
})
export class GoalboardComponent {
  games: Observable<FindAllGamesQuery['findGames']>;

  constructor(findAllGamesGQL: FindAllGamesGQL) {
    this.games = findAllGamesGQL
      .watch()
      .valueChanges.pipe(map((result) => result.data.findGames ?? []));
  }
}
