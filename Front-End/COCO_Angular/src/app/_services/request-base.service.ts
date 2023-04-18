import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from "../models/User";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export abstract class RequestBaseService {

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
