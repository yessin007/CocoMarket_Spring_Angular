import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Delivery} from "../models/delivery";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DeliveriesService {
  readonly ALL_DELIVERIES = 'http://localhost:8083/kaddem/api/delivery/retrive_all_deliveries';
  readonly CHANGE_STATUS = 'http://localhost:8083/kaddem/api/delivery/changeStatusToDelivered/';

  constructor(private httpClient: HttpClient) { }

  retrieveDeliveryList(){
    return this.httpClient.get<Delivery[]>(this.ALL_DELIVERIES);
  }


  changeStatusToDelivered(id: number): Observable<any>{
    return this.httpClient.put(this.CHANGE_STATUS + id, {});
  }
}
