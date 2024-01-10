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
    <h3 class="text-center">🎯 Scoreboard 🎯</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th class="text-center">Player Name</th>
          <th class="text-center">Rounds</th>
          <th class="text-center">Winner</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let game of games | async">
          <td class="text-center">{{ game?.playerId }}</td>
          <td class="text-center">{{ game?.rounds }}</td>
          <td class="text-center">{{ game?.winner }}</td>
        </tr>
      </tbody>
    </table>
  `,
})
export class ScoreboardComponent {
  games: Observable<FindAllGamesQuery['findGames']>;

  constructor(findAllGamesGQL: FindAllGamesGQL) {
    this.games = findAllGamesGQL
      .watch()
      .valueChanges.pipe(map((result) => result.data.findGames ?? []));
  }
}
