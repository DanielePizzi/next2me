import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { User } from '../model/model.user';
import { LoginService } from './login.service';
import { SessionService } from '../core/services/session.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  user: User=new User();
  errorMessage:string;

  constructor(private loginService: LoginService,
              private router: Router,
              private sessionService: SessionService
            ) { }

  ngAfterViewInit() {
    (window as any).initialize();
  }

  ngOnInit() {
  }

  login(){
    this.loginService.login(this.user).subscribe(data => {
      this.sessionService.changeToken(data.token_sessione);
      this.router.navigate(['/areaPrivata']);
    })
  }
}
