import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {Router} from "@angular/router";
import { User } from '../model/model.user';
import { AuthService } from '../services/auth.service';
import { LoaderService } from '../core/services/loader.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {

  user: User=new User();
  errorMessage:string;
  email: string;
  password: string;

  constructor(private authService :AuthService,
              private router: Router,
              private loaderService:LoaderService) { }

  ngAfterViewInit() {
    (window as any).initialize();
  }

  ngOnInit() {
  }

  login(){
    // this.authService.logIn(this.user)
    //   .subscribe(data=>{
    //     this.router.navigate(['/profile']);
    //     },err=>{
    //     this.errorMessage="error :  Username or password is incorrect";
    //     }
    //   )
    // this.loaderService.show();
  }
}
