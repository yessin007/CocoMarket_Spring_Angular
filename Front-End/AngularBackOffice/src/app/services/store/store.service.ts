import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Store} from '../../models/store';
import {Observable} from 'rxjs';
import {AuthService} from '../auth.service';
import {RequestBaseService} from '../request-base.service';
import {PosteStore} from "../../models/PostStore";


@Injectable({
  providedIn: 'root'
})
export class StoreService extends RequestBaseService{
  // tslint:disable-next-line:variable-name
  readonly ADD_Store = 'http://localhost:8089/maram/api/store/addStore';
  // tslint:disable-next-line:variable-name
  readonly Get_Store = 'http://localhost:8089/maram/api/store/get_all_Stores';
  // tslint:disable-next-line:variable-name
  readonly DELETE_Store = 'http://localhost:8089/maram/store/deleteStore/';

  readonly FIND_BY_ID = 'http://localhost:8089/maram/store/retrive_Store/';

  private apiUrl = 'http://localhost:8089/maram/store';


  constructor(private httpClient: HttpClient, private auth: AuthService) {
    // @ts-ignore
    super(auth, httpClient);

  }
  addStore(store: FormData): Observable<any> {
    return this.httpClient.post(this.ADD_Store, store ,{headers: this.getHeaders});
  }
  getAllStores() {
    return this.httpClient.get<Store[]>(this.Get_Store);
  }
  deleteStore(storeId: number){
    return this.httpClient.delete(this.DELETE_Store + storeId);
  }
  getStoreDetails(storeId){
    return this.httpClient.get<Store>(this.FIND_BY_ID + storeId);
  }
  affectProductToStore(storeId: number, productId: number): Observable<void> {
    const url = `${this.apiUrl}/affectproducttostore/${storeId}/${productId}`;
    return this.httpClient.put<void>(url, {});
  }
  addProductStore(storeId: number, productId: number): Observable<void> {
    const url = `${this.apiUrl}/affectproducttostore/${storeId}/${productId}`;
    return this.http.post<void>(url, null);
  }
  addPost(post: PosteStore) {
    return this.http.post<PosteStore>('http://localhost:8089/maram/api/store/add-Post/1', post, {headers: this.getHeaders});
  }
  postFile(courseId: string, file: File) {
    const formParams = new FormData();
    // @ts-ignore
    formParams.append('image', file);
    const options: { headers: HttpHeaders } = {
      headers: new HttpHeaders({
        'Content-Type': 'multipart/form-data'
      })
    };
    return this.http.post('http://localhost:8087/SpringMVC/forum/add-Post-image/' + courseId, formParams );
  }

}
