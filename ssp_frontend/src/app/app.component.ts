import { Component, Inject } from '@angular/core';
import { ToastService } from './services/toast.service';

@Component({
  selector: 'app-root',
  styles: `
  
   .container {
     max-width: 600px;
     border-radius: 10px;
     border: solid 1px grey;
     margin-top: 15px;
     margin-bottom: 15px;
     padding: 30px;
   }
 
`,
  template: `
    <div class="container">
      <app-navbar></app-navbar>
      <hr />
      <router-outlet></router-outlet>
    </div>
  `,
})
export class AppComponent {
  title = 'ssp_frontend';
}
