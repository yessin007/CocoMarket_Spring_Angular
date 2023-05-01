import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  readonly ADD_ORDERS = 'http://localhost:9090/kaddem/order/add_order';
  constructor(private httpClient: HttpClient) { }

  public addToCart(productId){
    return this.httpClient.get('http://localhost:9090/kaddem/cart/addToCArt/' + productId);
  }

}
