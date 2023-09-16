import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../models/User";
import {AuthenticationRequest} from "../models/AuthenticationRequest";
import {RegisterRequest} from "../models/RegisterRequest";
import {map} from "rxjs/operators";


const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
	providedIn: 'root'
})
export class AuthService {
	public currentUser!: Observable<User>;
	private currentUserSubject!: BehaviorSubject<User>;

	// tslint:disable-next-line:ban-types
	public currentToken!: Observable<String>;
	// tslint:disable-next-line:ban-types
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
		// tslint:disable-next-line:ban-types
		this.currentTokenSubject = new BehaviorSubject<String>(storageToken);
		this.currentToken = this.currentTokenSubject.asObservable();
	}

	private readonly API_URL = 'http://localhost:9092/COCO/api/v1/auth/';
	ar!: AuthenticationRequest;
	rr!: RegisterRequest;

	login(username: string, password: string): Observable<any> {
		this.ar = {username, password};
		console.log('login Service called');
		return this.http.post<any>(`${this.API_URL}webAuthenticate`, this.ar)
			.pipe(
				map(response => {
						if (response) {
							localStorage.setItem('currentUser', JSON.stringify(response.user));
							localStorage.setItem('token', JSON.stringify(response.token));
							this.currentUserSubject.next(response);
							console.log('login Service done successfully');
						}
						else {
							console.log('login Service failed');
						}
						return response;
					}
				)
			);
	}
	register(username: string, firstname: string, lastname: string, email: string, password: string): Observable<any> {
		const role = 'ROLE_BUYER';
		// @ts-ignore
		const rr: RegisterRequest = {username, firstname, lastname, email, password, role};
		return this.http.post(
			`${this.API_URL}register`,
			rr,
			httpOptions
		);
	}
	logOut(){
		localStorage.removeItem('currentUser');
		localStorage.removeItem('token');
		// tslint:disable-next-line:new-parens
		this.currentUserSubject.next(new User);
	}
	public isLoggedIn(): boolean {
		const storageUserAsStr = localStorage.getItem('token');
		if (storageUserAsStr){
			return true;
		}
		return false;
	}

	demResetPassword(email: string): Observable<any> {
		return this.http.post(
			`${this.API_URL}demResetPassword/${email}`,
			null,
			{ observe: 'response' } // observe the response to get the status code
		);
	}

	/*verifAccount(mail: string, code: number): Observable<string> {
		const url = `${this.API_URL}verif/${mail}/${code}`;
		return this.http.post<string>(url, null);
	}*/

	// tslint:disable-next-line:ban-types
	verifAccount(mail: string, code: number): Observable<Map<String, String>> {
		const url = `${this.API_URL}verif/${mail}/${code}`;
		return this.http.post<{ [key: string]: string }>(url, null).pipe(
			map(response => new Map(Object.entries(response)))
		);
	}


}
