import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {
  FindAllPlayersQueryGQL,
  FindAllPlayersQueryQuery,
} from '../../models/generated/graphql';

@Component({
  selector: 'app-players',
  template: `
    <h3 class="text-center">🤾 Players 🤾</h3>
    <table class="table table-striped">
      <thead>
        <tr>
          <th class="text-center">Player Name</th>
        </tr>
      </thead>
      <tbody>
        <tr *ngFor="let player of players | async">
          <td class="text-center">{{ player?.name }}</td>
        </tr>
      </tbody>
    </table>
    <button class="btn btn-primary">Add Player</button>
  `,
})
export class PlayersComponent {
  players: Observable<FindAllPlayersQueryQuery['findAllPlayers']>;

  constructor(findAllPlayersQueryGQL: FindAllPlayersQueryGQL) {
    this.players = findAllPlayersQueryGQL
      .watch()
      .valueChanges.pipe(map((result) => result.data.findAllPlayers ?? []));
  }
}
