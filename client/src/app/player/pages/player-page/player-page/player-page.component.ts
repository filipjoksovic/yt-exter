import {CommonModule} from '@angular/common';
import {ChangeDetectionStrategy, Component, type OnInit, Signal} from '@angular/core';
import {Router} from '@angular/router';
import {WebsocketService} from '../../../../core/services/websocket.service';
import {PlayerApiService} from "../../../services/api/player.api.service";
import {PlayerService} from "../../../services/player.service";
import {SearchApiService} from "../../../../search/services/search.api.service";
import {switchMap} from "rxjs";
import {toSignal} from "@angular/core/rxjs-interop";
import {SearchResultModel} from "../../../../search/models/search-result.model";
import {FormatSecondsPipe} from "../../../../videos/pipes/format-seconds.pipe";

@Component({
  selector: 'app-player-page',
  standalone: true,
  imports: [
    CommonModule,
    FormatSecondsPipe,
  ],
  templateUrl: './player-page.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PlayerPageComponent implements OnInit {

  nowWatching$ = toSignal(this.playerService.getNowWatching().pipe(switchMap((response) => {
    return this.searchService.getVideoDetails(response.content);
  }))) as Signal<SearchResultModel>;


  constructor(private router: Router, private readonly websocketService: WebsocketService, private readonly playerService: PlayerService, private readonly searchService: SearchApiService) {
  }

  ngOnInit(): void {
  }


  public navigateToHome(): void {
    // This is a comment
    this.router.navigate([""]);
  }

}
