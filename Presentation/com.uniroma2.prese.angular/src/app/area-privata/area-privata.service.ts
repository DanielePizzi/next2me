import { Injectable } from '@angular/core';
import { ApiService } from '../shared/api-service/api-service';

@Injectable()
export class AreaPrivataService {
  constructor(private apiService: ApiService) { }

  salvaPuntoInteresse(input: any){
    return this.apiService.post('salvaPuntoInteresse',input)
  }

  getPuntoInteresse(input: any){
    return this.apiService.post('getPuntoInteresse',input)
  }

  rimuoviPuntoInteresse(input: any){
    return this.apiService.post('rimuoviPuntoInteresse',input)
  }
}
