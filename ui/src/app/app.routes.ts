import {Routes} from '@angular/router';
import {ExchangesComponent} from "./exchanges.component";
import {ExchangesResolve} from "./services/api.service";


export const APP_ROUTES: Routes = [

  {
    path: 'exchanges',
    component: ExchangesComponent,
    resolve: {
      exchanges: ExchangesResolve
    }
  }
];
