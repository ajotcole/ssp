import { Component } from '@angular/core';
import { GameStageEnum } from '../../models/enums/gameStageEnum';
import {
  CreateGameWithRoundsDocument,
  CreateGameWithRoundsGQL,
  FindAllPlayersQueryGQL,
  FindAllPlayersQueryQuery,
  Player,
  Round,
} from '../../models/generated/graphql';
import { Observable, map } from 'rxjs';
import { ChoiceEnum } from '../../models/enums/choiceEnum';
import { playRockPaperScissors } from '../../services/gamelogic.service';
import { WinnerEnum } from '../../models/enums/winnerEnum';
import { Apollo } from 'apollo-angular';
import { RoundInput } from '../../../generated/graphql';

@Component({
  selector: 'app-game',
  styles: `
  .choiceCard {
    padding: 20px;
    margin-bottom: 5px;
    transition: box-shadow 0.2s;
    p {
      margin-bottom: 0;
      font-size: 30px;
    }
  }

  .humanSelectionMode {
    cursor: pointer;
    &:hover {
      box-shadow: 0px 0px 10px blue;
    }
  }

  .humanSelected {
    box-shadow: 0px 0px 10px blue;
  }

  .computerSelected {
    box-shadow: 0px 0px 10px red;
  }

  `,
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
          [class.btn-primary]="gameStage === GameStageEnum.START"
          [class.btn-secondary]="gameStage !== GameStageEnum.START"
          class="position-absolute top-0 start-0 translate-middle btn btn-sm btn-secondary rounded-pill"
          style="width: 2rem; height:2rem;"
        >
          1
        </button>
        <button
          type="button"
          [class.btn-primary]="gameStage === GameStageEnum.INGAME"
          [class.btn-secondary]="gameStage !== GameStageEnum.INGAME"
          class="position-absolute top-0 start-50 translate-middle btn btn-sm btn-secondary rounded-pill"
          style="width: 2rem; height:2rem;"
        >
          2
        </button>
        <button
          [class.btn-primary]="gameStage === GameStageEnum.END"
          [class.btn-secondary]="gameStage !== GameStageEnum.END"
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
        *ngIf="gameStage === GameStageEnum.START"
      >
        <p style="margin-top: 10px;">1. Select a player</p>
        <select
          (input)="selectedPlayerId = Number($any($event.target).value)"
          class="form-select"
        >
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
          (click)="gameStage = GameStageEnum.INGAME"
        >
          Start Game
        </button>
      </div>

      <div
        class="secondstep text-center"
        *ngIf="gameStage === GameStageEnum.INGAME"
      >
        <h2>Round {{ currentRound }} of {{ numberRounds }}</h2>
        <div class="container text-center">
          <div id="playingboard" class="row">
            <div class="col">
              <h4>Player Choice</h4>
              <div
                class="card choiceCard"
                (click)="clickHumanChoice(ChoiceEnum.STONE)"
                [class.humanSelectionMode]="
                  humanChoice === ChoiceEnum.UNTOUCHED
                "
                [class.humanSelected]="humanChoice === ChoiceEnum.STONE"
              >
                <h5 class="card-title">Stone</h5>
                <p>🪨</p>
              </div>
              <div
                class="card choiceCard"
                (click)="clickHumanChoice(ChoiceEnum.SCISSOR)"
                [class.humanSelectionMode]="
                  humanChoice === ChoiceEnum.UNTOUCHED
                "
                [class.humanSelected]="humanChoice === ChoiceEnum.SCISSOR"
              >
                <h5 class="card-title">Scissor</h5>
                <p>✂️</p>
              </div>
              <div
                class="card choiceCard"
                (click)="clickHumanChoice(ChoiceEnum.PAPER)"
                [class.humanSelectionMode]="
                  humanChoice === ChoiceEnum.UNTOUCHED
                "
                [class.humanSelected]="humanChoice === ChoiceEnum.PAPER"
              >
                <h5 class="card-title">Paper</h5>
                <p>📄</p>
              </div>
            </div>
            <div class="col">
              <h4>Computer Choice</h4>
              <div
                class="card choiceCard"
                [class.computerSelected]="computerChoice === ChoiceEnum.STONE"
              >
                <h5 class="card-title">Stone</h5>
                <p>🪨</p>
              </div>
              <div
                class="card choiceCard"
                [class.computerSelected]="computerChoice === ChoiceEnum.SCISSOR"
              >
                <h5 class="card-title">Scissor</h5>
                <p>✂️</p>
              </div>
              <div
                class="card choiceCard"
                [class.computerSelected]="computerChoice === ChoiceEnum.PAPER"
              >
                <h5 class="card-title">Paper</h5>
                <p>📄</p>
              </div>
            </div>
          </div>
          <div id="goalcounter" style="margin: 10px">
            <div *ngIf="roundWinner === WinnerEnum.UNTOUCHED">
              Please make a choice
            </div>
            <div *ngIf="roundWinner === WinnerEnum.HUMAN">
              Horray you win this round ✅
            </div>
            <div *ngIf="roundWinner === WinnerEnum.COMPUTER">
              Too bad, you lost ❌
            </div>
            <div *ngIf="roundWinner === WinnerEnum.TIE">Its a tie ⭕️</div>
          </div>
          <div class="row">
            <button
              *ngIf="currentRound < Number(numberRounds)"
              class="btn btn-primary"
              (click)="clickNextRound()"
              [class.disabled]="roundWinner === WinnerEnum.UNTOUCHED"
            >
              Next Round
            </button>
            <button
              *ngIf="currentRound === Number(numberRounds)"
              class="btn btn-primary"
              (click)="clickFinish()"
              [class.disabled]="roundWinner === WinnerEnum.UNTOUCHED"
            >
              Finish Game
            </button>
          </div>
        </div>
      </div>
      <div
        id="thirdstep"
        class="text-center"
        *ngIf="gameStage === GameStageEnum.END"
      >
        <h2>Finish</h2>
        <p>Rounds Overview</p>
        <table class="table table-striped">
          <thead>
            <tr>
              <th class="text-center">Round</th>
              <th class="text-center">Player Choice</th>
              <th class="text-center">Computer Choice</th>
              <th class="text-center">Round Winner</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let singleRound of roundData; index as i">
              <td class="text-center">{{ i + 1 }}</td>
              <td class="text-center">{{ singleRound.humanChoice }}</td>
              <td class="text-center">{{ singleRound.computerChoice }}</td>
              <td class="text-center">{{ singleRound.winner }}</td>
            </tr>
          </tbody>
        </table>

        <button class="btn btn-danger" (click)="clickRestart()">Restart</button>
      </div>
    </div>
  `,
})
export class GameComponent {
  GameStageEnum = GameStageEnum;
  ChoiceEnum = ChoiceEnum;
  WinnerEnum = WinnerEnum;
  Number = Number;
  selectedPlayerId: Number = 0;
  gameStage: GameStageEnum = GameStageEnum.START;
  humanChoice: ChoiceEnum = ChoiceEnum.UNTOUCHED;
  computerChoice: ChoiceEnum = ChoiceEnum.UNTOUCHED;
  numberRounds: number = 1;
  currentRound: number = 1;
  roundWinner: WinnerEnum = WinnerEnum.UNTOUCHED;
  roundData: RoundInput[] = [];
  players: Observable<FindAllPlayersQueryQuery['findAllPlayers']>;

  constructor(
    findAllPlayersQueryGQL: FindAllPlayersQueryGQL,
    private apollo: Apollo
  ) {
    this.players = findAllPlayersQueryGQL
      .watch()
      .valueChanges.pipe(map((result) => result.data.findAllPlayers ?? []));
  }

  getProgessBarSetting(gameStage: GameStageEnum) {
    switch (gameStage) {
      case GameStageEnum.START:
        return '0%';
      case GameStageEnum.INGAME:
        return '50%';
      case GameStageEnum.END:
        return '100%';
    }
  }

  saveGameDataToDb() {
    this.apollo
      .mutate<CreateGameWithRoundsGQL>({
        mutation: CreateGameWithRoundsDocument,
        variables: {
          playerId: this.selectedPlayerId,
          roundData: this.roundData,
        },
      })
      .subscribe(
        ({ data }) => {
          console.log('saved game data', data);
        },
        (error) => {
          console.log('there was an error sending the query', error);
        }
      );
  }

  // do game choice, computer choice and store in local state
  clickHumanChoice(option: ChoiceEnum) {
    if (this.humanChoice !== ChoiceEnum.UNTOUCHED) {
      return;
    }

    this.humanChoice = option;
    const calculatedRoundData = playRockPaperScissors(option);
    this.computerChoice = calculatedRoundData.computerChoice;
    this.roundWinner = calculatedRoundData.winner;
    this.roundData.push(calculatedRoundData);
  }

  // reset round for next one
  clickNextRound() {
    this.roundWinner = WinnerEnum.UNTOUCHED;
    this.humanChoice = ChoiceEnum.UNTOUCHED;
    this.computerChoice = ChoiceEnum.UNTOUCHED;
    this.currentRound = this.currentRound + 1;
  }

  // Finish game and send data to db
  clickFinish() {
    this.saveGameDataToDb();
    this.gameStage = GameStageEnum.END;
  }

  // Reset game
  clickRestart() {
    this.selectedPlayerId = 0;
    this.numberRounds = 1;
    this.currentRound = 1;
    this.roundWinner = WinnerEnum.UNTOUCHED;
    this.humanChoice = ChoiceEnum.UNTOUCHED;
    this.computerChoice = ChoiceEnum.UNTOUCHED;
    this.roundData = [];
    this.gameStage = GameStageEnum.START;
  }
}
