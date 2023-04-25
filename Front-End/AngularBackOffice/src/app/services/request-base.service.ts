import { Injectable } from '@angular/core';
import {AuthService} from './auth.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export abstract class RequestBaseService {

  // tslint:disable-next-line:ban-types
  protected currentToken!: String;

  protected constructor(protected authenticationService: AuthService, protected http: HttpClient) {
    this.authenticationService.currentToken.subscribe( data => {
      this.currentToken = data;
    });
  }

  get getHeaders(): HttpHeaders{
    return new HttpHeaders(
        {
          authorization: 'Bearer ' + this.currentToken ,
          'Content-Type': 'application/json; charset=UTF-8'
        }
    );
  }
}

