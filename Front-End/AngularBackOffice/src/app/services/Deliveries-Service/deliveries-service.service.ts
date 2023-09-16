import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Delivery } from 'src/app/models/delivery';

@Injectable({
  providedIn: 'root'
})

export class DeliveriesService {
  readonly ALL_DELIVERIES = 'http://localhost:9092/COCO/api/delivery/retrive_all_deliveries';
  readonly CHANGE_STATUS = 'http://localhost:9092/COCO/api/delivery/changeStatusToDelivered/';
  readonly DISPATCH = 'http://localhost:9092/COCO/api/delivery/dispatch/';

  constructor(private httpClient: HttpClient) { }

  retrieveDeliveryList(){
    return this.httpClient.get<Delivery[]>(this.ALL_DELIVERIES);
  }

  changeStatusToDelivered(id: number): Observable<any>{
    return this.httpClient.put(this.CHANGE_STATUS + id, {});
  }

  dispatch(id: number): Observable<any>{
    return this.httpClient.post(this.DISPATCH + id, {});
  }
}
