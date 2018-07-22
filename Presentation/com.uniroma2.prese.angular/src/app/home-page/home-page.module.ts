import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HomePageComponent } from './home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { AgmCoreModule } from '@agm/core';
import { AgmJsMarkerClustererModule } from '@agm/js-marker-clusterer';
import { SharedModule } from '../shared/shared.module';

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCRceEo_Xe9yYkhpjwPzrB8y_L2dCe4by0',
      libraries: ["places"]
    }),
    AgmJsMarkerClustererModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  declarations: [HomePageComponent],
  exports:[
    HomePageComponent,
    RouterModule
  ],
})
export class HomePageModule { }
