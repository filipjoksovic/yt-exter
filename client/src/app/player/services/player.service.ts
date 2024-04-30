import {Injectable, signal, WritableSignal} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {WebsocketService} from "../../core/services/websocket.service";
import {PlayerApiService} from "./api/player.api.service";
import {SearchResultModel} from "../../search/models/search-result.model";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  activeVideo: WritableSignal<SearchResultModel | null> = signal<SearchResultModel | null>(null);

  constructor(private readonly websocketService: WebsocketService, private readonly playerApiService: PlayerApiService) {
  }


  sendWatchMessage(embed_url: string) {
    this.websocketService.sendWatchMessage(embed_url);
  }

  public getNowWatching() {
    return this.playerApiService.getNowWatching();
  }

  public setActiveVideo(video: SearchResultModel) {
    this.activeVideo.set(video);

  }
}
