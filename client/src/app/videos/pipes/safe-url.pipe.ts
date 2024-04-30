import {Pipe, PipeTransform} from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Pipe({
  name: 'safeUrl',
  standalone: true
})
export class SafeUrlPipe implements PipeTransform {

  constructor(private readonly domSanitizer: DomSanitizer) {
  }

  transform(url: string): SafeResourceUrl {
    if (!url) throw new Error("Value for provided URL is undefined!");
    return this.domSanitizer.bypassSecurityTrustResourceUrl(url);
  }

}
