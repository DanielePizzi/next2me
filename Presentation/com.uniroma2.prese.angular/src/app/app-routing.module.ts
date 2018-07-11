import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { UrlPermission } from "./shared/urlPermission/url.permission";

const routes: Routes = [
  // otherwise redirect to profile
  // { path: '', redirectTo: '/login' , pathMatch: 'full'},
  // { path: 'profile', component: ProfileComponent ,canActivate: [UrlPermission] },
  // { path: 'login', component: LoginComponent },
  // { path: 'register', component: RegisterComponent }
  // otherwise redirect to profile
  { path: '', redirectTo: '/login' , pathMatch: 'full'},
  { path: 'profile', loadChildren: 'app/profile/profile.module#ProfileModule' ,canActivate: [UrlPermission] },
  { path: 'login', loadChildren: 'app/login/login.module#LoginModule' },
  { path: 'register', loadChildren: 'app/register/register.module#RegisterModule' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
