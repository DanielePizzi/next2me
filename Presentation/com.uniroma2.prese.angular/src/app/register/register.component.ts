import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Router} from "@angular/router";
import { User } from '../model/model.user';
import { RegisterService } from './register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  password2:string = null;
  errorMessage: string;
  barLabel: string = "Robustezza password:";

  constructor(
    private registerService: RegisterService,
    private router: Router) {}

  ngOnInit() {
  }

  register() {
    console.log(this.user);
    this.registerService.creaAccount(this.user).subscribe(data => {
      console.log(data);
      }, err => {
        console.log(err);
      }
    )
  }
}
