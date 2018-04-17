import { Routes, RouterModule } from "@angular/router";
import { ProfileComponent } from "./components/profile/profile.component";
import { UrlPermission } from "./urlPermission/url.permission";
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { NgModule } from "@angular/core";

const routes: Routes = [
  // otherwise redirect to profile
  { path: '', redirectTo: '/login' , pathMatch: 'full'},
  { path: 'profile', component: ProfileComponent ,canActivate: [UrlPermission] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
