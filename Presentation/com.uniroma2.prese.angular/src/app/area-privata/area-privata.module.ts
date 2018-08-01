import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AgmCoreModule } from '@agm/core';
import { AgmJsMarkerClustererModule } from '@agm/js-marker-clusterer';
import { SharedModule } from '../shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {RatingModule} from "ngx-rating";
import { AreaPrivataComponent } from './area-privata.component';

const routes: Routes = [
  {
    path: '',
    component: AreaPrivataComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    AgmCoreModule,
    AgmJsMarkerClustererModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    NgbModule,
    RatingModule
  ],
  declarations: [AreaPrivataComponent],
  exports:[
    AreaPrivataComponent,
    RouterModule
  ],
})
export class AreaPrivataModule { }
