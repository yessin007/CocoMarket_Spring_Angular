import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Store} from '../../models/store';
import {Observable} from 'rxjs';
import {Provider} from '../../models/provider';


@Injectable({
  providedIn: 'root'
})
export class StoreService {
  // tslint:disable-next-line:variable-name
  readonly ADD_Store = 'http://localhost:8089/kaddem/store/addStore';
  // tslint:disable-next-line:variable-name
  readonly Get_Store = 'http://localhost:8089/kaddem/store/get_all_Stores';
  // tslint:disable-next-line:variable-name
  readonly DELETE_Store = 'http://localhost:8089/kaddem/store/deleteStore/';

  readonly FIND_BY_ID = 'http://localhost:8089/kaddem/store/xget_all_Stores';



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

  editStore(storeId){
    return this.httpClient.get<Provider>(this.FIND_BY_ID + storeId);
  }
}
