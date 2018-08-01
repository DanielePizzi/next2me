import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class UrlPermission implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (localStorage.getItem('token_sessione')) {
      // se sono loggato ritorna true
      return true;
    }
    // se non sei loggato ritorna alla login
    this.router.navigate(['/login']);
    return false;
  }
}
