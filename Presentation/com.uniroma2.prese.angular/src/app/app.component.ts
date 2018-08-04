import { Component, OnInit, Input } from '@angular/core';
import { LoaderService } from './core/services/loader.service';
import { LoaderState } from './shared/interface/loader-state';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { SessionService } from './core/services/session.service';

@Component({
  selector: 'ngbd-modal-content',
  styles: ['.modal-header{background-color: red;}'] ,
  template: `
    <div class="modal-header">
      <h4 class="modal-title">{{title}}</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <div *ngFor="let error of errors">
        <p>{{error}}!</p>
      </div>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Chiudi</button>
    </div>
  `
})

export class NgbdModalContentError {
  @Input() errors;
  @Input() title;

  constructor(
    public activeModal: NgbActiveModal
  ) {}
}

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
      private sessionService: SessionService
    ) {
  }

  ngOnInit(): void {
    this.loaderService.loaderState.subscribe((val: LoaderState) => {
      this.showLoader = val;
    });
    this.sessionService.currentSessionToken.subscribe(token => this.token = token)
  }

  logout() {
    this.sessionService.deleteToken();
  }

}
