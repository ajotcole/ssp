import { Component } from '@angular/core';
import { GameStage } from '../../models/enums/gameStageEnum';
import {
  FindAllPlayersQueryGQL,
  FindAllPlayersQueryQuery,
  Round,
} from '../../models/generated/graphql';
import { Observable, map } from 'rxjs';

@Component({
  selector: 'app-game',
  template: `
    <div>
      <h3 class="text-center">🕹️ Game 🕹️</h3>

      <div class="position-relative m-4">
        <div class="progress" role="progressbar" style="height: 3px;">
          <div
            class="progress-bar"
            [style]="{
              width: getProgessBarSetting(gameStage)
            }"
          ></div>
        </div>
        <button
          type="button"
          [class.btn-primary]="gameStage === GameStage.START"
          [class.btn-secondary]="gameStage !== GameStage.START"
          class="position-absolute top-0 start-0 translate-middle btn btn-sm btn-secondary rounded-pill"
          style="width: 2rem; height:2rem;"
        >
          1
        </button>
        <button
          type="button"
          [class.btn-primary]="gameStage === GameStage.INGAME"
          [class.btn-secondary]="gameStage !== GameStage.INGAME"
          class="position-absolute top-0 start-50 translate-middle btn btn-sm btn-secondary rounded-pill"
          style="width: 2rem; height:2rem;"
        >
          2
        </button>
        <button
          [class.btn-primary]="gameStage === GameStage.END"
          [class.btn-secondary]="gameStage !== GameStage.END"
          type="button"
          class="position-absolute top-0 start-100 translate-middle btn btn-sm btn-secondary rounded-pill"
          style="width: 2rem; height:2rem;"
        >
          3
        </button>
      </div>
      <div
        id="firststep"
        class="text-center"
        *ngIf="gameStage === GameStage.START"
      >
        <p style="margin-top: 10px;">1. Select a player</p>
        <select class="form-select">
          <option
            *ngFor="let player of players | async"
            value="{{ player?.id }}"
          >
            {{ player?.name }}
          </option>
        </select>
        <p style="margin-top: 10px;">2. Select amount of rounds</p>
        <select
          (input)="numberRounds = $any($event.target).value"
          class="form-select"
        >
          <option [value]="1">1</option>
          <option [value]="2">2</option>
          <option [value]="3">3</option>
          <option [value]="3">4</option>
          <option [value]="3">5</option>
        </select>

        <button
          class="btn btn-success"
          style="margin: 15px"
          (click)="gameStage = GameStage.INGAME"
        >
          Start Game
        </button>
      </div>

      <div
        class="secondstep text-center"
        *ngIf="gameStage === GameStage.INGAME"
      >
        <h2>Round {{ currentRound }} of {{ numberRounds }}</h2>

        <button (click)="gameStage = GameStage.END">Next</button>
      </div>
      <div
        id="thirdstep"
        class="text-center"
        *ngIf="gameStage === GameStage.END"
      >
        <button class="btn btn-danger" (click)="gameStage = GameStage.START">
          Restart
        </button>
      </div>
    </div>
  `,
})
export class GameComponent {
  selectedPlayer: string | null = null;
  gameStage: GameStage = GameStage.START;
  GameStage = GameStage;
  numberRounds: Number = 1;
  currentRound: Number = 1;
  roundData: Round[] = [];
  players: Observable<FindAllPlayersQueryQuery['findAllPlayers']>;

  constructor(findAllPlayersQueryGQL: FindAllPlayersQueryGQL) {
    this.players = findAllPlayersQueryGQL
      .watch()
      .valueChanges.pipe(map((result) => result.data.findAllPlayers ?? []));
  }

  getProgessBarSetting(gameStage: GameStage) {
    switch (gameStage) {
      case GameStage.START:
        return '0%';
      case GameStage.INGAME:
        return '50%';
      case GameStage.END:
        return '100%';
    }
  }
}
