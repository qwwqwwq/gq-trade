import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs/Observable';
import {Exchange} from "../models"
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";


export interface ApiService {
  getExchanges(): Observable<Exchange[]>
}

@Injectable()
export class ApiServiceImpl implements ApiService {

  constructor(private httpClient: HttpClient) {
  }

  getExchanges(): Observable<Exchange[]> {
    return this.httpClient.get<Exchange[]>("/api/exchanges");
  }
}

@Injectable()
export class ExchangesResolve implements Resolve<Exchange[]> {

  constructor(private apiService: ApiService) {
  }

  resolve(route: ActivatedRouteSnapshot): Observable<Exchange[]> {
    return this.apiService.getExchanges();
  }
}

