import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { AuthService } from './services/auth.service';
import { AppRoutingModule } from './app-routing.module';
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { UrlPermission } from './shared/urlPermission/url.permission';
import { ApiService } from './shared/api-service/api-service';
import { environments } from '../environments/ALL-ENVIRONMENTS';
import { environment } from '../environments/environment';
import { EnvironmentService } from './shared/environment-service/environment-service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AgmCoreModule } from '@agm/core';

export function loadEnv(environments,environment) {
  return new EnvironmentService(environments,environment)
}

@NgModule({
  declarations: [
    AppComponent
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
      apiKey: 'AIzaSyCRceEo_Xe9yYkhpjwPzrB8y_L2dCe4by0',
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
    AuthService,
    UrlPermission],
  bootstrap: [AppComponent]
})
export class AppModule { }
