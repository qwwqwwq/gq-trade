import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs/Observable';
import {Test} from "../models"


export interface ApiService {
  getExchanges(): Array<Test>
}

@Injectable()
export class ApiServiceImpl implements ApiService {
  constructor(private httpClient: HttpClient) {
  }

  getExchanges(): any[] {
    throw new Error("Method not implemented.");
  }
}
