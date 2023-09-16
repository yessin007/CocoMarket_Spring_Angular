import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Provider} from '../../models/provider';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {
  readonly ADD_PROVIDER = 'http://localhost:9092/COCO/api/provider/add-provider';
  readonly GET_ALL_PROVIDER = 'http://localhost:9092/COCO/api/provider/getAllProviders';
  readonly DELETE_PROVIDER = 'http://localhost:9092/COCO/api/provider/deleteProvider/';
  readonly FIND_BY_ID = 'http://localhost:9092/COCO/api/provider/getProviderDetails/';
  readonly ASSIGN_LOCATION = 'http://localhost:9092/COCO/api/provider/setLatLng/';

  constructor(private httpClient: HttpClient) { }

  addProvider(provider: Provider): Observable<any> {
    return this.httpClient.post(this.ADD_PROVIDER, provider);
  }
  getAllProvider() {
    return this.httpClient.get<Provider[]>(this.GET_ALL_PROVIDER);
  }
  deleteProvider(id: number){
    return this.httpClient.delete(this.DELETE_PROVIDER + id);
  }

  editProvider(id){
    return this.httpClient.get<Provider>(this.FIND_BY_ID + id);
  }
  setLatLngToProvider(id: number){
    return this.httpClient.get<Provider>(this.ASSIGN_LOCATION + id);
  }



}
