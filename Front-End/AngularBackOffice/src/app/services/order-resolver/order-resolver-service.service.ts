import { Injectable } from '@angular/core';
import {ProductService} from '../product/product.service';
import {OrderService} from '../order/order.service';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {Product} from '../../models/product';
import {map} from 'rxjs/operators';
import {Order} from '../../models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderResolverServiceService {

  constructor(private orderservice: OrderService) { }
  order: Order = new Order();
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Order> {
    const id = route.paramMap.get('orderId');
    if (id){
      return this.orderservice.getOrderDetails(id);
    } else {
      return of(this.getOrderDetail());
    }
  }

 /* resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product> {
    const id = route.paramMap.get('orderId');
    if (id){
      return this.orderservice.getOrderDetails(id).pipe(
          map(p => this.imageProcessingService.createImages(p))
      );
    }else {
      return of(this.getOrderDetail());
    }
  }*/
  getOrderDetail(){
    return this.order;
  }
}
