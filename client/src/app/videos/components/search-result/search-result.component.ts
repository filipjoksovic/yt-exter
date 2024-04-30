import {Component, inject, Input} from '@angular/core';
import {SearchResultModel} from "../../../search/models/search-result.model";
import {PlayerService} from "../../../player/services/player.service";
import {SafeUrlPipe} from "../../pipes/safe-url.pipe";
import {SearchService} from '../../../search/services/search.service';
import {FormatSecondsPipe} from '../../pipes/format-seconds.pipe';
import {Thumbnail} from '../../../search/models/thumbnail.model';
import {SlicePipe} from '@angular/common';

@Component({
  selector: 'app-search-result',
  standalone: true,
  imports: [
    SafeUrlPipe,
    FormatSecondsPipe,
    SlicePipe
  ],
  templateUrl: './search-result.component.html',
})
export class SearchResultComponent {
  private _result!: SearchResultModel;

  @Input()
  set result(value: SearchResultModel) {
    this.searchService.getVideoDetails(value?.watchUrl || '').subscribe({
      next: (result) => {
        this._result = {...result, ...this._result};
      },
      error: (error) => {
        console.error("Error: ", error);
      }
    });
    this._result = value;
  }

  get result(): SearchResultModel {
    return this._result;
  }

  private playerService = inject(PlayerService);
  private searchService = inject(SearchService);

  watchSuggestion(embed_url: string) {
    this.playerService.sendWatchMessage(embed_url);
    this.playerService.setActiveVideo(this.result);

  }
}
