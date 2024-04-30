import { Routes } from '@angular/router';
import { HomePageComponent } from "./home/pages/home-page/home-page.component";
import { PlayerPageComponent } from './player/pages/player-page/player-page/player-page.component';

export const routes: Routes = [
  {
    path: "",
    component: HomePageComponent,
  },
  {
    path: "player",
    component: PlayerPageComponent
  }
];
