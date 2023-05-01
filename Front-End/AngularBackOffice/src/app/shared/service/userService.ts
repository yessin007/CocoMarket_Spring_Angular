import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from "../../models/User";
import { RequestBaseService } from "../../services/request-base.service";
import { AuthService } from "../../services/auth.service";

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

	getAllUsers(): Observable<User[]> {
		return this.httpClient.get<User[]>(`${this.baseUrl}web`, { headers: this.getHeaders });
	}

	getUserById(id: number): Observable<User> {
		return this.httpClient.get<User>(`${this.baseUrl}${id}`, { headers: this.getHeaders });
	}

	createUser(user: User): Observable<User> {
		return this.httpClient.post<User>(this.baseUrl, user, { headers: this.getHeaders });
	}

	updateUser(user: User): Observable<User> {
		return this.httpClient.put<User>(`${this.baseUrl}${user.id}`, user, { headers: this.getHeaders });
	}

	deleteUser(id: number): Observable<any> {
		return this.httpClient.delete(`${this.baseUrl}${id}`, { headers: this.getHeaders });
	}

	updateRoleUser(id: number, role: string): Observable<string> {
		return this.httpClient.post<string>(`${this.baseUrl}updateRole/${id}/${role}`, null, { headers: this.getHeaders });
	}

	blockUser(id: number, duration: number): Observable<string> {
		return this.httpClient.put<string>(`${this.baseUrl}block/${id}/${duration}`, null, { headers: this.getHeaders });
	}

	unblockUser(id: number): Observable<string> {
		return this.httpClient.put<string>(`${this.baseUrl}unblock/${id}`, null, { headers: this.getHeaders });
	}

	resetPassword(code: number, pwd: string): Observable<string> {
		return this.httpClient.post<string>(`${this.baseUrl}ResetPassword/${code}/${pwd}`, null, { headers: this.getHeaders });
	}

	findInterestsOfBuyers(userId: number): Observable<any> {
		return this.httpClient.get<any>(`${this.baseUrl}findtheinterestsofbuyers/${userId}`, { headers: this.getHeaders });
	}

	getAllUserFavorites(userId: number): Observable<any> {
		return this.httpClient.get<any>(`${this.baseUrl}getAllUserFavories/${userId}`, { headers: this.getHeaders });
	}

	setLatLngToUser(userId: number): Observable<any> {
		return this.httpClient.get<any>(`${this.baseUrl}setLatLng/${userId}`, { headers: this.getHeaders });
	}


}
