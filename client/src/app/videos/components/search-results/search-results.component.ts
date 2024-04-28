import { Component, OnInit, Signal } from '@angular/core';
import { SearchService } from "../../../search/services/search.service";
import { Observable, tap } from "rxjs";
import { AsyncPipe, NgForOf, NgIf } from "@angular/common";
import { SafeUrlPipe } from "../../pipes/safe-url.pipe";
import { SearchResultModel } from "../../../search/models/search-result.model";
import { PlayerService } from "../../../player/services/player.service";
import { SearchResultComponent } from "../search-result/search-result.component";
import { DataState } from '../../../models/shared/state.model';
import { toSignal } from '@angular/core/rxjs-interop';
import { DataStateEnum } from '../../../models/shared/data-state.enum';

@Component({
  selector: 'app-search-results',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe,
    NgForOf,
    SafeUrlPipe,
    SearchResultComponent
  ],
  templateUrl: './search-results.component.html',
  styleUrl: './search-results.component.css'
})
export class SearchResultsComponent implements OnInit {

  searchResults!: Signal<DataState<SearchResultModel[]>>;
  DataStateEnum = DataStateEnum;

  constructor(private readonly searchService: SearchService, private readonly playerService: PlayerService) {
    this.searchResults = toSignal(this.searchService.searchResultsMin$) as Signal<DataState<SearchResultModel[]>>;

  }

  ngOnInit(): void {
  }


}
