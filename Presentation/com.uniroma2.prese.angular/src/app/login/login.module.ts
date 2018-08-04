import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login.component';
import '../../assets/login-animation.js';
import { LoginService } from './login.service';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(routes)
  ],
  declarations: [LoginComponent],
  exports:[
    LoginComponent,
    RouterModule
  ],
  providers: [LoginService],
})
export class LoginModule { }
