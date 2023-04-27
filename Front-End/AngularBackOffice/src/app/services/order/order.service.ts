import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Order} from '../../models/order';
import {Provider} from "../../models/provider";
import {Product} from "../../models/product";
import {RequestBaseService} from "../request-base.service";
import {AuthService} from "../auth.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OrderService extends RequestBaseService{
  readonly ADD_ORDERS = 'http://165.227.171.67:9092/COCO/api/order/add_order';
  readonly UPDATE_ORDERS = 'http://localhost:9090/kaddem/order/update_order';
  readonly GETALL_ORDERS = 'http://localhost:9090/kaddem/order/retrive_all_orders';
  readonly DELETE_ORDERS = 'http://localhost:9090/kaddem/order/delete_order/';
  readonly GET_ORDER_DETAILS__API_URL = 'http://localhost:9090/kaddem/order/getorderdetails/';

  // tslint:disable-next-line:no-shadowed-variable
  constructor( http: HttpClient,  authenticationService: AuthService) {
    super(authenticationService, http);
  }
  /*getComplaints(): Observable<Complaint[]>{
    return this.http.get<Complaint[]>('http://localhost:8087/SpringMVC/complaint/affichReclamation',{headers: this.getHeaders});
  }*/
  addOrder(order: Order): Observable<any> {
    return this.http.post(this.ADD_ORDERS, order,{headers: this.getHeaders});
  }
  getAllOrders(){
    return this.http.get<Order[]>(this.GETALL_ORDERS);
  }
  deleteOrder(orderId: number){
    return this.http.delete(this.DELETE_ORDERS + orderId);
  }
  updateOrder(order: FormData): Observable<any>{
    return this.http.put(this.UPDATE_ORDERS, order);
  }

  updateeOrder(orderId: number): Observable<any>{
    return this.http.put(this.UPDATE_ORDERS, orderId);
  }



  getOrderDetails(orderId){
    return this.http.get<Order>(this.GET_ORDER_DETAILS__API_URL + orderId);
  }


  // editProvider(id){
  //   return this.http.get<Provider>(this.FIND_BY_ID + id);
  // }
}
