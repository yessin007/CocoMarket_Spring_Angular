import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

const state = {
  checkoutItems: JSON.parse(localStorage.checkoutItems || '[]')
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private router: Router) { }

  // Get Checkout Items
  public get checkoutItems(): Observable<any> {
    const itemsStream = new Observable(observer => {
      observer.next(state.checkoutItems);
      observer.complete();
    });
    return itemsStream as Observable<any>;
  }

  // Create order
  public createOrder(product: any, details: any, orderId: any, amount: any) {
    let item = {
        shippingDetails: details,
        product,
        orderId,
        totalAmount: amount
    };
    state.checkoutItems = item;
    localStorage.setItem('checkoutItems', JSON.stringify(item));
    localStorage.removeItem('cartItems');
    this.router.navigate(['/shop/checkout/success', orderId]);
  }

}
