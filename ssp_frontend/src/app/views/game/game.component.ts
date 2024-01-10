import { Component } from '@angular/core';

@Component({
  selector: 'app-game',
  template: `
    <div>
      <h3 class="text-center">🕹️ Game 🕹️</h3>

      <div class="position-relative m-4">
        <div class="progress" role="progressbar" style="height: 1px;">
          <div class="progress-bar" style="width: 0%"></div>
        </div>
        <button
          type="button"
          class="position-absolute top-0 start-0 translate-middle btn btn-sm btn-primary rounded-pill"
          style="width: 2rem; height:2rem;"
        >
          1
        </button>
        <button
          type="button"
          class="position-absolute top-0 start-50 translate-middle btn btn-sm btn-secondary rounded-pill"
          style="width: 2rem; height:2rem;"
        >
          2
        </button>
        <button
          type="button"
          class="position-absolute top-0 start-100 translate-middle btn btn-sm btn-secondary rounded-pill"
          style="width: 2rem; height:2rem;"
        >
          3
        </button>
      </div>
      <div class="stepone" *ngIf="false">
        <p>1. Please select a player</p>
        <div ngbDropdown class="d-inline-block">
          <button
            type="button"
            class="btn btn-outline-primary"
            id="dropdownBasic1"
            ngbDropdownToggle
          >
            Toggle dropdown
          </button>
          <div ngbDropdownMenu aria-labelledby="dropdownBasic1">
            <button ngbDropdownItem>Action - 1</button>
          </div>
        </div>
      </div>

      <div class="secondstep text-center" *ngIf="true">
        <h2>Please Choose</h2>
      </div>
      <div class="thirdstep" *ngIf="true"></div>
    </div>
  `,
})
export class GameComponent {
  selectedPlayer: string | null = null;
}
