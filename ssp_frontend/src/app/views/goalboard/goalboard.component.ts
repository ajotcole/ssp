import { Component } from '@angular/core';

@Component({
  selector: 'app-goalboard',
  template: `

  <p>goalboard works! {{name}}</p>
  <button type="button" class="btn btn-outline-primary" (click)="triggerCounter()" >Primary</button>
  <h2>{{counter}}</h2>
  <p *ngIf="show">Count above 20<p>


  `

})
export class GoalboardComponent {
  name = 'Test variable';
  counter = 0;
  show = false;



  public triggerCounter() {
    this.counter = this.counter + 1;

    if(this.counter > 19) {
      this.show = true;
    }

  }


}
