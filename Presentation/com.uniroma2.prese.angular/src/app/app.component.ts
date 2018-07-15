import { Component, OnInit } from '@angular/core';
import { LoaderService } from './core/services/loader.service';
import { LoaderState } from './interface/loader-state';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  showLoader: LoaderState;

  constructor(
      private loaderService: LoaderService) {
  }

  ngOnInit(): void {
    this.loaderService.loaderState.subscribe((val: LoaderState) => {
      this.showLoader = val;
    });
  }

}
