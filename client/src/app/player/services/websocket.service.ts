import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private websocket: WebSocket;

  private _open$ = new Subject<void>();
  public open$ = this._open$.asObservable();
  private _message$ = new Subject<string>();
  public message$ = this._message$.asObservable();
  private _close$ = new Subject<void>();
  public close$ = this._close$.asObservable();

  constructor() {
    this.websocket = new WebSocket('ws://192.168.1.125:8080/player/app');

    this.websocket.onopen = (e) => {
      console.log("[open] Connection established");
      this._open$.next();
    };

    this.websocket.onmessage = (event) => {
      console.log(`[message] Data received from server: ${event.data}`);
      this._message$.next(event.data);
    };

    this.websocket.onclose = (event) => {
      if (event.wasClean) {
        console.log(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
      } else {
        // e.g. server process killed or network down
        // event.code is usually 1006 in this case
        console.log('[close] Connection died');
      }
      this._close$.next();
    };

    this.websocket.onerror = function (error) {
      console.log(`[error]`, error);
    };
  }

  sendWatchMessage(embed_url: string) {
    this.websocket.send(embed_url);
  }
}
