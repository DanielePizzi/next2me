import { Component, OnInit, Input, Injector } from '@angular/core';
import { LoaderService } from './core/services/loader.service';
import { LoaderState } from './shared/interface/loader-state';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { SessionService } from './core/services/session.service';
import { LoggedService } from './core/services/loggedService';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  showLoader: LoaderState;
  token: string;

  constructor(
      private loaderService: LoaderService,
      private sessionService: SessionService,
      private loggedService: LoggedService,
    ) {
      loggedService.isLogged().subscribe(data => {
        if(data) {
          this.sessionService.changeToken(data);
        }
      })
  }

  ngOnInit(): void {

    this.loaderService.loaderState.subscribe((val: LoaderState) => {
      this.showLoader = val;
        this.sessionService.currentSessionToken.subscribe(token => this.token = token)
    });
  }

  logout() {
    this.loggedService.logout().subscribe(data => {
      if(data) {
        this.sessionService.deleteToken();
      }
    })
  }
}
