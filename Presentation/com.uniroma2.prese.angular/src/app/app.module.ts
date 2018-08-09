import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent} from './app.component';
import { HttpModule } from '@angular/http';
import { AppRoutingModule } from './app-routing.module';
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { ApiService } from './shared/api-service/api-service';
import { environments } from '../environments/ALL-ENVIRONMENTS';
import { environment } from '../environments/environment';
import { EnvironmentService } from './shared/environment-service/environment-service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AgmCoreModule } from '@agm/core';
import { NgbdModalContentError } from './shared/components/NgbdModalContentError';

export function loadEnv(environments, environment) {
  return new EnvironmentService(environments, environment)
}

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule,
    CoreModule,
    SharedModule,
    NgbModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCXOEdgZmeen95WzI3ACAFPIpFTzIDPdUI',
      libraries: ["places"]
    }),
  ],
  providers: [
    {provide: 'ENVIRONMENTS',useValue: environments},
    {provide: 'DEFAULT_ENVIRONMENT',useValue:environment},
    {
      provide: EnvironmentService,
      useFactory: loadEnv,
      deps: ['ENVIRONMENTS','DEFAULT_ENVIRONMENT']
    },
    ApiService,
  ],
  entryComponents:[NgbdModalContentError],
  bootstrap: [AppComponent]
})
export class AppModule { }
