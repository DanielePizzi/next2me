import { Injectable } from '@angular/core';
import { ApiService } from '../shared/api-service/api-service';
import { User } from '../model/model.user';

@Injectable()
export class LoginService {

  constructor(private apiService: ApiService) { }

  login(user:User){
    return this.apiService.post('login',user)
  }

}
