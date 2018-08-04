import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoaderService } from './services/loader.service';
import { SessionService } from './services/session.service';
import { UrlPermission } from './services/url.permission';

@NgModule({
  imports: [
    CommonModule
  ],
  providers : [
    LoaderService,
    SessionService,
    UrlPermission
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
