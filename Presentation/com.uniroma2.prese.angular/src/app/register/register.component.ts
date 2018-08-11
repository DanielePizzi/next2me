import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import { User } from '../model/model.user';
import { RegisterService } from './register.service';
import { SessionService } from '../core/services/session.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  password2:string = null;
  errorMessage: string;
  barLabel: string = "Robustezza password:";

  constructor(
    private registerService: RegisterService,
    private router: Router,
    private sessionService: SessionService
  ) {}

  ngOnInit() {
  }

  register() {
    this.registerService.creaAccount(this.user).subscribe(data => {
      this.sessionService.changeToken(data.token_sessione);
      this.router.navigate(['/areaPrivata']);
    })
  }
}
