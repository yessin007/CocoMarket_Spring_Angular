import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Order} from '../../models/order';
import {Provider} from "../../models/provider";
import {Product} from "../../models/product";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  readonly ADD_ORDERS = 'http://localhost:9092/COCO/api/order/add_order';
  readonly UPDATE_ORDERS = 'http://localhost:9090/kaddem/order/update_order';
  readonly GETALL_ORDERS = 'localhost:9092/COCO/api/order/retrive_all_orders';
  readonly DELETE_ORDERS = 'http://localhost:9090/kaddem/order/delete_order/';
  readonly GET_ORDER_DETAILS_API_URL = 'http://localhost:9090/kaddem/order/retrive_order/';

  constructor(private httpClient: HttpClient) { }

  addOrder(order: Order): Observable<any> {
    return this.httpClient.post(this.ADD_ORDERS, order);
  }


  getAllOrders(){
    return this.httpClient.get<Order[]>(this.GETALL_ORDERS);
  }
  deleteOrder(orderId: number){
    return this.httpClient.delete(this.DELETE_ORDERS + orderId);
  }
  updateOrder(order: FormData): Observable<any>{
    return this.httpClient.put(this.UPDATE_ORDERS, order);
  }

  updateeOrder(orderId: number): Observable<any>{
    return this.httpClient.put(this.UPDATE_ORDERS, orderId);
  }



  getOrderDetails(orderId){
    return this.httpClient.get<Order>(this.GET_ORDER_DETAILS_API_URL + orderId);
  }


  // editProvider(id){
  //   return this.httpClient.get<Provider>(this.FIND_BY_ID + id);
  // }
}
