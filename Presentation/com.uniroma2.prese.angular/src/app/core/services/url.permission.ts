import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { SessionService } from '../../core/services/session.service';

@Injectable()
export class UrlPermission implements CanActivate {

  constructor(
    private router: Router,
    private sessionService: SessionService,
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (this.sessionService.getToken()) {
      // se sono loggato ritorna true
      return true;
    }
    // se non sei loggato ritorna alla login
    this.router.navigate(['/login']);
    return false;
  }
}
