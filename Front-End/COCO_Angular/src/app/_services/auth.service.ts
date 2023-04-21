import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../models/User";
import {BehaviorSubject, map, Observable} from "rxjs";
import {AuthenticationRequest} from "../models/AuthenticationRequest";
import {RegisterRequest} from "../models/RegisterRequest";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public currentUser!: Observable<User>;
  private currentUserSubject!: BehaviorSubject<User>;

  public currentToken!: Observable<String>;
  private currentTokenSubject!: BehaviorSubject<String>;
  constructor(private http: HttpClient) {
    let storageUser;
    const storageUserAsStr = localStorage.getItem('currentUser');
    if (storageUserAsStr){
      try {
        storageUser = JSON.parse(storageUserAsStr);
      } catch (e) {
        console.error('Error parsing storageUserAsStr', e);
      }
    }
    this.currentUserSubject = new BehaviorSubject<User>(storageUser);
    this.currentUser = this.currentUserSubject.asObservable();

    let storageToken;
    const storageTokenAsStr = localStorage.getItem('token');
    if (storageTokenAsStr){
      try {
        storageToken = JSON.parse(storageTokenAsStr);
      } catch (e) {
        console.error('Error parsing storageTokenAsStr', e);
      }
    }
    this.currentTokenSubject = new BehaviorSubject<String>(storageToken);
    this.currentToken = this.currentTokenSubject.asObservable();
  }

  private readonly API_URL = 'http://165.227.171.67:9092/COCO/api/v1/auth/';
  ar!:AuthenticationRequest;
  rr!:RegisterRequest;

  login(username: string, password: string): Observable<any> {
    this.ar = {username, password};
    return this.http.post<any>(`${this.API_URL}webAuthenticate`, this.ar)
      .pipe(
        map(response => {
            if (response) {
              localStorage.setItem('currentUser', JSON.stringify(response.user));
              localStorage.setItem('token', JSON.stringify(response.token));
              this.currentUserSubject.next(response);
            }
            return response;
          }
        )
      );
  }
  logOut(){
    localStorage.removeItem('currentUser');
    localStorage.removeItem('token');
    this.currentUserSubject.next(new User);
  }
  public isLoggedIn(): boolean {
    let storageUser;
    const storageUserAsStr = localStorage.getItem('token');
    if (storageUserAsStr){
      return true;
    }
    return false;
  }
}
