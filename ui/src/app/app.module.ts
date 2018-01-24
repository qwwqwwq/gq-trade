import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';


import {AppComponent} from './app.component';
import {APP_ROUTES} from "./app.routes";
import {ExchangesComponent} from "./exchanges.component";
import {API_SERVICE, ApiServiceImpl, ExchangesResolve} from "./services/api.service";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    ExchangesComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(APP_ROUTES),
    HttpClientModule
  ],
  providers: [
    { provide: API_SERVICE, useClass: ApiServiceImpl },
    ExchangesResolve
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
