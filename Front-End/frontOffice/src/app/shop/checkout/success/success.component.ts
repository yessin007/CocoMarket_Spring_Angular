import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Order } from '../../../shared/classes/order';
import { OrderService } from '../../../shared/services/order.service';
import { ProductService } from '../../../shared/services/product.service';
import {Product} from "../../../shared/classes/product";
import {CartItem} from "../../../shared/classes/CartItem";
import {Cart} from "../../../shared/classes/Cart";
import {ImageProcessingService} from "../../../shared/services/image-processing.service";

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit, AfterViewInit{

  public orderDetails: Order = {};
  public products: Product[] = [];
  public cartItems: CartItem[] = [];
  cart: Cart[] = [];

  constructor(public productService: ProductService,  private imageProcessingService: ImageProcessingService,
    private orderService: OrderService) { }

  ngOnInit(): void {	
    this.orderService.checkoutItems.subscribe(response => this.orderDetails = response);
    this.getCartItemsWithProducts();
  }

  ngAfterViewInit() {
    
  }
  public get getTotal():number {
    //return this.productService.getCartTotalAmount();
    let totalAmount = 0;
    for (const cartItem of this.cartItems) {
      let price = cartItem.product.price;
      if (cartItem.product.discount) {
        price = (cartItem.product.price) * ( 1 - cartItem.product.discount / 100 );
      }
      totalAmount += price * cartItem.quantity;
    }
    return totalAmount +7;

  }

  public cartId: number = 1;

  public getCartItemsWithProducts() {
    this.productService.getCartItemsWithProducts(this.cartId).subscribe(
        (cartItems) => {
          this.cartItems = cartItems;
          this.cartItems.forEach((cartItem) => {
            this.productService.getProduct(cartItem.product.productId).subscribe(
                (product) => {
                  const updatedProduct = this.imageProcessingService.createImages(product);
                  cartItem.product = updatedProduct;
                },
                (error) => {
                  console.log(error);
                }
            );
          });
        },
        (error) => {
          console.log(error);
        }
    );
  }

}
