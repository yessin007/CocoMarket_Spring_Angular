import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../models/User";
import {BehaviorSubject, Observable} from "rxjs";
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
  }
  private readonly API_URL = 'http://165.227.171.67:9092/COCO/api/v1/auth/';
  ar!:AuthenticationRequest;
  rr!:RegisterRequest;
}
