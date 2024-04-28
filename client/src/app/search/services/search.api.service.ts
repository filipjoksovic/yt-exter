import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { SearchResultModel } from "../models/search-result.model";
import { Observable } from "rxjs";
import { SearchResultMinifiedModel } from '../models/search-result-minified.model';

@Injectable({
  providedIn: 'root'
})
export class SearchApiService {


  constructor(private readonly http: HttpClient) {
  }

  searchForVideo(value: string): Observable<SearchResultModel[]> {

    return this.http.get<SearchResultModel[]>(`http://192.168.1.125:8080/search?term=${value}`);

  }

  searchForVideoMin(value: string | null): Observable<import("../models/search-result-minified.model").SearchResultMinifiedModel[]> {
    return this.http.get<SearchResultMinifiedModel[]>(`http://192.168.1.125:8080/search/minified?term=${value}`);
  }

  getVideoDetails(videoId: string): Observable<SearchResultModel> {
    return this.http.post<SearchResultModel>(`http://192.168.1.125:8080/details`, { "video_url": videoId });
  }
}
