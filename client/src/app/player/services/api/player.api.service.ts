import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {HttpClient} from "@angular/common/http";

interface NowPlayingResponse {
  content: string;
}

@Injectable({
  providedIn: 'root'
})
export class PlayerApiService {

  constructor(private readonly http: HttpClient) {
  }

  public getNowWatching() {
    return this.http.get<NowPlayingResponse>('http://192.168.1.125:8080/now-playing/now-playing');
  }
}
