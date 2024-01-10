import { Component } from '@angular/core';
import { routes } from '../../app-routing.module';
import { NavigationEnd, Router, Routes } from '@angular/router';

@Component({
  selector: 'app-navbar',
  template: `
    <p class="text-center" style="font-size: 60px; margin: 0;">🪨✂️📄</p>
    <h1 class="text-center">Stone, Scissor, Paper</h1>
    <p class="text-center">Minigame</p>
    <ul ngbNav class="nav nav-pills nav-justified" style="margin-bottom: 15px;">
      <li class="nav-item" *ngFor="let route of providedRoutes">
        <a
          class="nav-link"
          [routerLink]="[route.path]"
          routerLinkActive="active"
          href="{{ route.path }}"
          >{{ route.title }}</a
        >
      </li>
    </ul>
  `,
})
export class NavbarComponent {
  providedRoutes: Routes = routes;
}
