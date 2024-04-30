import {Injectable} from '@angular/core';
import {WebsocketService} from "./websocket.service";
import {SocketMessageType} from "../../models/core/socket-message.model";
import {filter} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WebsocketHandlerService {

  public playConfirmedMessage$ = this.websocketService.message$.pipe(filter(message => message.type === SocketMessageType.PLAY_CONFIRMED));

  constructor(private readonly websocketService: WebsocketService) {
    this.playConfirmedMessage$.pipe().subscribe({
      next: (message) => {
        console.log("Play confirmed: ", message);
      },
      error: (error) => {
        console.error("Error: ", error);
      }
    })
  }
}
