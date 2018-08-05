import { Injectable } from '@angular/core';
import { ApiService } from '../../shared/api-service/api-service';

@Injectable()
export class LoggedService {

  constructor(private apiService: ApiService) { }

  isLogged(){
    return this.apiService.get('isLogged');
  }

  logout(){
    return this.apiService.get('logout');
  }
}
