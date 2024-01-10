import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  styles: `
  
   .container {
     max-width: 600px;
   }
 
`,
  template: `
    <div class="container">
      <app-navbar></app-navbar>
      <router-outlet></router-outlet>
    </div>
  `,
})
export class AppComponent {
  title = 'ssp_frontend';
}
