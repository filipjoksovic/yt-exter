import {Component, OnInit} from '@angular/core';
import {AsyncPipe, NgIf, SlicePipe} from "@angular/common";
import {BehaviorSubject, Observable} from "rxjs";
import {PlayerService} from "../../services/player.service";
import {SafeUrlPipe} from "../../../videos/pipes/safe-url.pipe";
import {WebsocketHandlerService} from "../../../core/services/websocket-handler.service";
import {SocketMessage} from "../../../models/core/socket-message.model";
import {Router} from "@angular/router";

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

  activeVideo = this.playerService.activeVideo;

  constructor(private readonly playerService: PlayerService, private readonly websocketHandlerService: WebsocketHandlerService, private readonly router: Router) {
  }

  ngOnInit() {
    // this.websocketHandlerService.getNowPlayingMessage$.subscribe((data: SocketMessage) => {
    //   console.log('play confirm message received', data);
    // });
  }

  goToPlayerPage() {
    this.router.navigate(["/player"]);
  }
}
