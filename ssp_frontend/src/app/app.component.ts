import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <h1>Welcome to SSP Minigame</h1>
    <router-outlet></router-outlet>
    <button type="button" class="btn btn-outline-primary">Primary</button>
  `
})
export class AppComponent {
  title = 'ssp_frontend';
}
