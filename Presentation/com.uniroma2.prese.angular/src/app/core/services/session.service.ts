import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class SessionService {

  private sessionToken = new BehaviorSubject(null);
  currentSessionToken = this.sessionToken.asObservable();

  constructor() { }

  changeToken(sessionToken: string) {
    this.sessionToken.next(sessionToken)
  }

  deleteToken() {
    this.sessionToken.next(null)
  }

  getToken() {
    return this.sessionToken.value
  }

}
