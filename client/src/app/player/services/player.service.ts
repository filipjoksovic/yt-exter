import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {WebsocketService} from "./websocket.service";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  public activeStreamUrl$: BehaviorSubject<string> = new BehaviorSubject<string>("");

  constructor(private readonly websocketService: WebsocketService) {
  }

  setActiveStream(embed_url: string) {
    this.activeStreamUrl$.next(embed_url);
  }

  sendWatchMessage(embed_url: string) {
    this.websocketService.sendWatchMessage(embed_url);
  }
}
