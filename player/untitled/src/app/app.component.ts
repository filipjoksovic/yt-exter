import {Component, inject, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {WebsocketService} from "./services/websocket.service";
import {SafePipe} from "./pipes/safe.pipe";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SafePipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'untitled';

  websocketService = inject(WebsocketService);
  sanitizer = inject(DomSanitizer);
  embedUrl!: SafeResourceUrl;


  ngOnInit() {
    this.websocketService.message$.subscribe((message) => {
      console.log(message);
      this.embedUrl = this.sanitizer.bypassSecurityTrustResourceUrl(message);
    });
  }
}
