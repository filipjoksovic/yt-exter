import { Component, ElementRef, inject, OnInit, ViewChild } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { WebsocketService } from "./services/websocket.service";
import { SafePipe } from "./pipes/safe.pipe";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { log } from "@angular-devkit/build-angular/src/builders/ssr-dev-server";
import { SocketMessageType } from './model/socket-message.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SafePipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  @ViewChild("iframe")
  iframe!: ElementRef;
  title = 'untitled';

  websocketService = inject(WebsocketService);
  sanitizer = inject(DomSanitizer);
  embedUrl!: SafeResourceUrl;


  ngOnInit() {
    this.websocketService.message$.subscribe((message) => {
      switch (message.type) {
        case SocketMessageType.PLAY:
          this.playVideo(message.content || '');
          break;
        case SocketMessageType.PAUSE:
          this.pauseVideo();
          break;
        case SocketMessageType.NOW_PLAYING:
          this.stopVideo();
          break;
        default:
          console.error("Invalid message type");
      }

    });
  }
  stopVideo() {
    throw new Error('Method not implemented.');
  }
  pauseVideo() {
    throw new Error('Method not implemented.');
  }

  public playVideo(embedUrl: string) {
    console.log(embedUrl);
    if (!embedUrl.includes("youtube.com/embed/")) {
      console.error("Invalid URL");
      return;
    }
    this.embedUrl = this.sanitizer.bypassSecurityTrustResourceUrl(embedUrl + "?autoplay=1");
    setTimeout(() => {
      const event = new MouseEvent("click", {
        view: window,
        bubbles: true,
        cancelable: true
      })

      this.iframe.nativeElement.querySelector(".ytp-large-play-button").dispatchEvent(event);
      console.log("Clicked")
    }, 5000)
  }

}
