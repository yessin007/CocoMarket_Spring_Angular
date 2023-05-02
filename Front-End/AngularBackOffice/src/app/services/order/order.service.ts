import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Order} from '../../models/order';
import {Provider} from "../../models/provider";
import {Product} from "../../models/product";
import {RequestBaseService} from '../request-base.service';
import {AuthService} from '../auth.service';
import {Payement} from "../../models/Payement";

@Injectable({
  providedIn: 'root'
})

  export class OrderService extends RequestBaseService{
  readonly ADD_ORDERS = 'http://localhost:8089/radhwen/api/order/add_order';
  readonly UPDATE_ORDERS = 'http://localhost:8089/radhwen/api/order/update_order';
  readonly GETALL_ORDERS = 'http://localhost:8089/radhwen/api/order/retrive_all_orders';
  readonly DELETE_ORDERS = 'http://localhost:8089/radhwen/api/order/delete_order/';
  readonly GET_ORDER_DETAILS_API_URL = 'http://localhost:8089/radhwen/api/order/retrive_order/';
  readonly GETALLPAYMENT = 'http://localhost:8089/radhwen/api/payement/retrive_all_payements';

  //////////////////////////////////
  readonly ADD_PAYEMENT = 'http://localhost:8089/radhwen/api/payement/add_payement';
  constructor(private httpClient: HttpClient, private auth: AuthService) {
	  super(auth, httpClient);
  }

  addOrder(order: Order): Observable<any> {
	  // debugger
    return this.httpClient.post(this.ADD_ORDERS, order,{headers: this.getHeaders});
  }

  addPayement(payement: Payement): Observable<any> {
    // debugger
    return this.httpClient.post(this.ADD_PAYEMENT, payement,{headers: this.getHeaders});
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
