import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Store} from '../../models/store';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class StoreService {
  // tslint:disable-next-line:variable-name
  readonly ADD_Store = 'http://localhost:8089/maram/store/addStore';
  // tslint:disable-next-line:variable-name
  readonly Get_Store = 'http://localhost:8089/maram/store/get_all_Stores';
  // tslint:disable-next-line:variable-name
  readonly DELETE_Store = 'http://localhost:8089/maram/store/deleteStore/';

  readonly FIND_BY_ID = 'http://localhost:8089/maram/store/retrive_Store/';

  private apiUrl = 'http://localhost:8089/maram/store';


  constructor(private httpClient: HttpClient) { }
  addStore(store: FormData): Observable<any> {
    return this.httpClient.post(this.ADD_Store, store);
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

}
