import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgIf, SlicePipe } from "@angular/common";
import { BehaviorSubject, Observable } from "rxjs";
import { PlayerService } from "../../services/player.service";
import { SafeUrlPipe } from "../../../videos/pipes/safe-url.pipe";

@Component({
  selector: 'app-player',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe,
    SafeUrlPipe,
    SlicePipe
  ],
  templateUrl: './player.component.html',
  styleUrl: './player.component.css'
})
export class PlayerComponent implements OnInit {
  activeStream$!: Observable<string>;

  constructor(private readonly playerService: PlayerService) {
  }

  ngOnInit() {
  }

}
