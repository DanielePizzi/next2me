import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register.component';
import { RegisterService } from './register.service';
import { SharedModule } from '../shared/shared.module';

const routes: Routes = [
  {
    path: '',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    RouterModule.forChild(routes)
  ],
  declarations: [RegisterComponent],
  exports:[
    RegisterComponent,
    RouterModule
  ],
  providers: [RegisterService],
})
export class RegisterModule { }
