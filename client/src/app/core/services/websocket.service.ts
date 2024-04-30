import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {SocketMessage, SocketMessageType} from '../../models/core/socket-message.model';

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private websocket: WebSocket;

  private _open$ = new Subject<void>();
  public open$ = this._open$.asObservable();

  private _message$ = new Subject<SocketMessage>();
  public message$ = this._message$.asObservable();

  private _close$ = new Subject<void>();
  public close$ = this._close$.asObservable();

  private _nowPlaying$ = new Subject<string>();
  private nowPlaying$ = this._nowPlaying$.asObservable();

  constructor() {
    this.websocket = this.initializeWebsocket();

    this.message$.subscribe((message) => {
        console.log("Received message: ", message);
      }
    );
  }

  initializeWebsocket() {
    const socket = new WebSocket('ws://192.168.1.125:8080/player/app');

    socket.onopen = (e) => {
      console.log("[open] Connection established");
      this._open$.next();
    };

    function parseSocketMessage(data: string): SocketMessage {
      try {
        return JSON.parse(data) as SocketMessage;
      } catch (e) {
        console.error(`Failed to parse message: ${data}`);
        throw e;
      }

    }

    socket.onmessage = (event) => {
      console.log(`[message] Data received from server: ${event.data}`);
      const parsedMessage = parseSocketMessage(event.data);
      this._message$.next(parsedMessage);
    };

    socket.onclose = (event) => {
      if (event.wasClean) {
        console.log(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
      } else {
        // e.g. server process killed or network down
        // event.code is usually 1006 in this case
        console.log('[close] Connection died');
      }
      this._close$.next();
      this.initializeWebsocket();
    };

    socket.onerror = function (error) {
      console.log(`[error]`, error);
    };
    return socket;
  }

  sendWatchMessage(embed_url: string) {
    this.sendSocketMessage({type: SocketMessageType.PLAY, content: embed_url});
  }

  getNowPlaying() {
    this.sendSocketMessage({
      type: SocketMessageType.NOW_PLAYING
    });
  }

  public sendSocketMessage(message: SocketMessage) {
    this.websocket.send(JSON.stringify(message));
  }
}
