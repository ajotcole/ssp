import { Component } from '@angular/core';
import { routes } from '../../app-routing.module';
import { NavigationEnd, Router, Routes } from '@angular/router';

@Component({
  selector: 'app-navbar',
  template: `
    <h1 class="text-center">Stone, Scissor, Rock Minigame</h1>
    <ul ngbNav class="nav nav-pills nav-justified">
      <li class="nav-item" *ngFor="let route of providedRoutes">
        <a class="nav-link " href="{{ route.path }}">{{ route.title }}</a>
      </li>
    </ul>
  `,
})
export class NavbarComponent {
  providedRoutes: Routes = routes;
}
