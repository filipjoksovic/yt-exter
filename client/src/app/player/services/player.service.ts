import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {WebsocketService} from "../../core/services/websocket.service";
import {PlayerApiService} from "./api/player.api.service";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private readonly websocketService: WebsocketService, private readonly playerApiService: PlayerApiService) {
  }


  sendWatchMessage(embed_url: string) {
    this.websocketService.sendWatchMessage(embed_url);
  }

  public getNowWatching() {
    return this.playerApiService.getNowWatching();
  }
}
