import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Store} from '../../models/store';
import {Observable} from 'rxjs';
import {PosteStore} from "../../models/PostStore";
import {Product} from "../../models/product";


@Injectable({
  providedIn: 'root'
})
export class StoreService {
  // tslint:disable-next-line:variable-name
  readonly ADD_Store = 'http://localhost:9092/COCO/api/store/addStore';
  // tslint:disable-next-line:variable-name
  readonly Get_Store = 'http://localhost:9092/COCO/api/store/get_all_Stores';
  // tslint:disable-next-line:variable-name
  readonly DELETE_Store = 'http://localhost:9092/COCO/api/store/deleteStore/';

  readonly FIND_BY_ID = 'http://localhost:9092/COCO/api/store/retrive_Store/';

  private apiUrl = 'http://localhost:9092/COCO/api/store';


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
  addProductStore(storeId: number, productId: number): Observable<void> {
    const url = `${this.apiUrl}/affectproducttostore/${storeId}/${productId}`;
    return this.httpClient.post<void>(url, null);
  }
  addPost(post: PosteStore) {
    return this.httpClient.post<PosteStore>('http://localhost:9092/COCO/api/store/add-Post/1', post);
  }


  // getProductsByStore(storeId: number): Observable<Product[]> {
  //   const url = `${this.apiUrl}/getProductsByStore/${storeId}`;
  //   return this.http.put<Product[]>(url);
  // }
  getProductsByStore( id: number ){
    return this.httpClient.get<Product[]>('http://localhost:9092/COCO/api/store/getProductsByStore/' + id);
  }
}
