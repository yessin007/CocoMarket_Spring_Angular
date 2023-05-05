import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from "../../models/User";
import { RequestBaseService } from "../../services/request-base.service";
import { AuthService } from "../../services/auth.service";
import axios from "axios";

@Injectable({
	providedIn: 'root'
})
export class UserService extends RequestBaseService {
	// baseUrl = 'http://165.227.171.67:9092/COCO/api/users/';
	baseUrl = 'http://localhost:9092/COCO/api/users/';

	constructor(private httpClient: HttpClient, private auth: AuthService) {
		super(auth, httpClient);
	}

	/*getAllUsers(): Observable<User[]> {
		return this.httpClient.get<User[]>(this.baseUrl, { headers: this.getHeaders });
	}*/
/*
	const // @ts-ignore
	API_URL = 'http://165.227.171.67:9092/COCO/api/users/';
	async getAllUsers() {
		axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
		const response =await  axios.get(this.API_URL +'web');
		return response;
	}*/
	getAllUsers(): Observable<User[]> {
		return this.httpClient.get<User[]>(`${this.baseUrl}web`);
	}

	getUserById(id: number): Observable<User> {
		return this.httpClient.get<User>(`${this.baseUrl}${id}`);
	}

	createUser(user: User): Observable<User> {
		return this.httpClient.post<User>(this.baseUrl, user);
	}

	updateUser(user: User): Observable<User> {
		return this.httpClient.put<User>(`${this.baseUrl}${user.id}`, user);
	}

	deleteUser(id: number): Observable<any> {
		return this.httpClient.delete(`${this.baseUrl}${id}`);
	}

	updateRoleUser(id: number, role: string): Observable<string> {
		return this.httpClient.post<string>(`${this.baseUrl}updateRole/${id}/${role}`, null);
	}

	blockUser(id: number, duration: number): Observable<string> {
		return this.httpClient.put<string>(`${this.baseUrl}block/${id}/${duration}`, null);
	}

	unblockUser(id: number): Observable<string> {
		return this.httpClient.put<string>(`${this.baseUrl}unblock/${id}`, null);
	}

	resetPassword(code: number, pwd: string): Observable<string> {
		return this.httpClient.post<string>(`${this.baseUrl}ResetPassword/${code}/${pwd}`, null);
	}

	findInterestsOfBuyers(userId: number): Observable<any> {
		return this.httpClient.get<any>(`${this.baseUrl}findtheinterestsofbuyers/${userId}`);
	}

	getAllUserFavorites(userId: number): Observable<any> {
		return this.httpClient.get<any>(`${this.baseUrl}getAllUserFavories/${userId}`);
	}

	setLatLngToUser(userId: number): Observable<any> {
		return this.httpClient.get<any>(`${this.baseUrl}setLatLng/${userId}`);
	}


}
