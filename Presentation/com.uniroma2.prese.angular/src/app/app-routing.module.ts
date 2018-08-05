import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { UrlPermissionAreaPrivata, UrlPermissionLogging } from "./core/services/url.permission";

const routes: Routes = [
  { path: '', redirectTo: '/homePage' , pathMatch: 'full'},
  { path: 'homePage', loadChildren: 'app/home-page/home-page.module#HomePageModule'},
  { path: 'login', loadChildren: 'app/login/login.module#LoginModule' , canActivate: [UrlPermissionLogging]  },
  { path: 'register', loadChildren: 'app/register/register.module#RegisterModule' , canActivate: [UrlPermissionLogging]  },
  { path: 'areaPrivata', loadChildren: 'app/area-privata/area-privata.module#AreaPrivataModule', canActivate: [UrlPermissionAreaPrivata] },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
