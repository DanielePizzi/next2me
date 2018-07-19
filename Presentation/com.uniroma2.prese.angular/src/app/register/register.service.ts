import { Injectable } from '@angular/core';
import {User} from "../model/model.user";
import { ApiService } from '../shared/api-service/api-service';

@Injectable()
export class RegisterService {
  constructor(private apiService: ApiService) { }

  creaAccount(user:User){
    return this.apiService.post('register',user)
  }
}
