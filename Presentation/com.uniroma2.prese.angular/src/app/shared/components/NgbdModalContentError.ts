import { Component, Input } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

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
