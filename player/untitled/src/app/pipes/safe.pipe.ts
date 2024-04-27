import {Pipe, PipeTransform} from '@angular/core';
import {DomSanitizer, SafeHtml, SafeResourceUrl, SafeScript, SafeStyle, SafeUrl} from "@angular/platform-browser";

@Pipe({
  name: 'safe',
  standalone: true
})
export class SafePipe implements PipeTransform {

  constructor(protected _sanitizer: DomSanitizer) {
  }

  transform(value: string) {
    return this._sanitizer.bypassSecurityTrustUrl(value);
  }

}
