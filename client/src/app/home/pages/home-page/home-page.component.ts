import {Component} from '@angular/core';
import {SearchInputComponent} from "../../../search/components/search-input/search-input.component";
import {SearchResultComponent} from "../../../videos/components/search-result/search-result.component";
import {SearchResultsComponent} from "../../../videos/components/search-results/search-results.component";
import {PlayerComponent} from "../../../player/components/player/player.component";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    SearchInputComponent,
    SearchResultsComponent,
    PlayerComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

}
