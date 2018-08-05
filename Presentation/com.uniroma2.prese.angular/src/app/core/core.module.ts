import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoaderService } from './services/loader.service';
import { SessionService } from './services/session.service';
import { UrlPermissionAreaPrivata, UrlPermissionLogging } from './services/url.permission';
import { LoggedService } from './services/loggedService';

@NgModule({
  imports: [
    CommonModule
  ],
  providers : [
    LoaderService,
    SessionService,
    UrlPermissionAreaPrivata,
    UrlPermissionLogging,
    LoggedService
  ],
  declarations: []
})

export class CoreModule {
  constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule è stato già caricato. Importare solamente in appModule');
    }
  }
 }
