import { Injectable } from '@angular/core';
import { BehaviorSubject, EMPTY, Observable, tap, throwError } from "rxjs";
import { SearchApiService } from "./search.api.service";
import { SearchResultModel } from "../models/search-result.model";
import { DataState, initialDataState, loadedDataState, loadingDataState } from '../../models/shared/state.model';
import { DataStateEnum } from '../../models/shared/data-state.enum';
import { SearchResultMinifiedModel } from '../models/search-result-minified.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private readonly searchTerm$: BehaviorSubject<string> = new BehaviorSubject<string>("");
  private readonly _searchResults$: BehaviorSubject<DataState<SearchResultModel[]>> = new BehaviorSubject<DataState<SearchResultModel[]>>(initialDataState<SearchResultModel[]>());
  public readonly searchResults$: Observable<DataState<Array<SearchResultModel>>> = this._searchResults$.asObservable();

  private readonly _searchResultsMin$: BehaviorSubject<DataState<SearchResultMinifiedModel[]>> = new BehaviorSubject<DataState<SearchResultMinifiedModel[]>>(initialDataState<SearchResultMinifiedModel[]>());
  public readonly searchResultsMin$: Observable<DataState<Array<SearchResultMinifiedModel>>> = this._searchResultsMin$.asObservable();

  constructor(private readonly searchApiService: SearchApiService) {
  }

  searchForVideo(value: string | null): Observable<SearchResultModel[]> {
    if (!value) return EMPTY;

    this._searchResults$.next(loadingDataState());

    return this.searchApiService.searchForVideo(value).pipe(tap({
      next: (response: any) => {
        console.log("Nexting results")
        this._searchResults$.next(loadedDataState(response));
      },
      error: (response: any) => throwError(() => new Error("Error occurred when fetching videos"))
    }));
  }

  searchForVideoMin(value: string | null): Observable<SearchResultMinifiedModel[]> {
    if (!value) {
      console.error("No search term provided, returning EMPTY");
      return EMPTY;
    };

    this._searchResultsMin$.next(loadingDataState());

    return this.searchApiService.searchForVideoMin(value).pipe(tap({
      next: (response: any) => {
        console.log("Nexting results")
        this._searchResultsMin$.next(loadedDataState(response));
      },
      error: (response: any) => throwError(() => new Error("Error occurred when fetching videos"))
    }));
  }

  getVideoDetails(videoId: string): Observable<SearchResultModel> {
    return this.searchApiService.getVideoDetails(videoId);
  }

}
