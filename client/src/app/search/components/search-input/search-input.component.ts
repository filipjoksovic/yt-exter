import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { SearchService } from "../../services/search.service";
import { resolve } from "@angular/compiler-cli";

@Component({
  selector: 'app-search-input',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './search-input.component.html',
  styleUrl: './search-input.component.css'
})
export class SearchInputComponent implements OnInit {

  searchForm!: FormGroup<{
    searchInput: FormControl<string | null>
  }>

  constructor(private readonly formBuilder: FormBuilder, private readonly searchService: SearchService) {
  }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      searchInput: new FormControl("", Validators.required)
    })
  }

  searchForVideo() {
    console.log("Searching for a video");
    this.searchService.searchForVideoMin(this.searchForm.controls.searchInput.value).subscribe({
      next: (result) => {
        console.log("Result: ", result);
      },
      error: (error) => {
        console.error("Error: ", error);
        alert("Error: " + JSON.stringify(error));
      }
    });
  }
}
