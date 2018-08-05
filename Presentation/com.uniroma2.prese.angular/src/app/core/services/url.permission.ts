import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { SessionService } from '../../core/services/session.service';
import { LoggedService } from './loggedService';
import { Observable } from 'rxjs';

@Injectable()
export class UrlPermissionAreaPrivata implements CanActivate {

  constructor(
    private router: Router,
    private sessionService: SessionService,
    private loggedService: LoggedService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    return this.loggedService.isLogged().map(data => {
      if (data) {
          // se sono loggato ritorna true
          this.sessionService.changeToken(data);
          return true;
      }
      }).catch(() => {
          this.router.navigate(['/login']);
          this.sessionService.changeToken(null);
          return Observable.of(false);
      });
  }
}

@Injectable()
export class UrlPermissionLogging implements CanActivate {

  constructor(
    private router: Router,
    private sessionService: SessionService,
    private loggedService: LoggedService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    return this.loggedService.isLogged().map(data => {
      if (data) {
          // se sono loggato ritorna true
          this.router.navigate(['/homePage']);
          this.sessionService.changeToken(data);
          return false;
      }
      }).catch(() => {
          this.sessionService.changeToken(null);
          return Observable.of(true);
      });
  }
}
