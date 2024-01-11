import { Component, TemplateRef, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {
  CreateGameWithRoundsDocument,
  CreatePlayerDocument,
  CreatePlayerMutation,
  FindAllPlayersQueryDocument,
  FindAllPlayersQueryGQL,
  FindAllPlayersQueryQuery,
  Player,
} from '../../models/generated/graphql';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Apollo } from 'apollo-angular';

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
    <button class="btn btn-primary" (click)="open(content)">Add Player</button>
    <ng-template #content let-modal>
      <div class="modal-header">
        <h4 class="modal-title" id="modal-basic-title">Add Player</h4>
        <button
          type="button"
          class="btn-close"
          aria-label="Close"
          (click)="modal.dismiss('Cross click')"
        ></button>
      </div>
      <div class="modal-body">
        <div class="input-group mb-3">
          <span class="input-group-text">Name</span>
          <input
            type="text"
            class="form-control"
            placeholder="Please enter name"
            (input)="newPlayerName = $any($event.target).value"
          />
        </div>
      </div>
      <div class="modal-footer">
        <button
          type="button"
          class="btn btn-outline-dark"
          (click)="modal.close('saveclick')"
        >
          Save
        </button>
      </div>
    </ng-template>
  `,
})
export class PlayersComponent {
  players: Observable<FindAllPlayersQueryQuery['findAllPlayers']>;
  newPlayerName: String = '';
  private modalService = inject(NgbModal);
  closeResult = '';

  constructor(
    findAllPlayersQueryGQL: FindAllPlayersQueryGQL,
    private apollo: Apollo
  ) {
    this.players = findAllPlayersQueryGQL
      .watch()
      .valueChanges.pipe(map((result) => result.data.findAllPlayers ?? []));
  }

  fetchPlayers() {}

  saveNewPlayer() {
    this.apollo
      .mutate({
        mutation: CreatePlayerDocument,
        variables: {
          name: this.newPlayerName,
        },
        refetchQueries: [{ query: FindAllPlayersQueryDocument }],
      })
      .subscribe(
        ({ data }) => {
          console.log('got data', data);
        },
        (error) => {
          console.log('there was an error sending the query', error);
        }
      );
  }

  open(content: TemplateRef<any>) {
    this.modalService.open(content).result.then(() => {
      this.saveNewPlayer();
      this.newPlayerName = '';
    });
  }
}
