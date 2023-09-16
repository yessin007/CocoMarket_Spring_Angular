import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService } from "../../shared/services/product.service";
import { Product } from "../../shared/classes/product";
import {response} from "express";
import {Cart} from "../../shared/classes/cart";
import {ImageProcessingService} from "../../shared/services/image-processing.service";
import {HttpErrorResponse} from "@angular/common/http";
import {map} from "rxjs/operators";
import {CartItem} from "../../shared/classes/CartItem";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  public products: Product[] = [];
  public cartItems: CartItem[] = [];
  cart: Cart[] = [];
  ngOnInit(): void {
    //this.getCartDetails();
    //this.CartgetProductDetails();
    this.getCartItemsWithProducts();
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
    return totalAmount;

  }

  public cartId: number = 1;

  //public cartItemProduct:cartItems.product[] = [];


  constructor(public productService: ProductService, private imageProcessingService: ImageProcessingService) {
    // tslint:disable-next-line:no-shadowed-variable
    this.productService.cartItems.subscribe(response => this.products = response);
  }
  // public getCartItemsWithProducts(){
  //   this.productService.getCartItemsWithProducts(this.cartId)
  //       .subscribe(cartItems => {
  //         this.cartItems = cartItems;
  //         this.cartItems.forEach(cartItem => {
  //           this.productService.getProduct(cartItem.product.productId).pipe(
  //               map((x: Product, i) => x.map((cartItem1: CartItem) => this.imageProcessingService.createImagess(cartItem1)))
  //           )
  //               .subscribe(product => {
  //                 cartItem.product = product;
  //               });
  //         });
  //       });

  // }


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
  //public getCartItemsWithProducts() {
  //   this.productService.getCartItemsWithProducts(this.cartId).pipe(
  //       map((cartItems: CartItem[]) =>
  //           cartItems.map((cartItem: CartItem) =>
  //               ({ ...cartItem, product: this.imageProcessingService.createImages(cartItem.product) })
  //           )
  //       )
  //   ).subscribe(
  //       (cartItemsWithProducts: CartItem[]) => {
  //         console.log(cartItemsWithProducts);
  //         this.cartItems = cartItemsWithProducts;
  //       },
  //       (error: HttpErrorResponse) => { console.log(error); }

  //   );

  // }


  public CartgetProductDetails(){
    this.productService.CartgetAllProducts().pipe(
        map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
    ).subscribe(
        (resp: Product[]) => {console.log(resp); this.products = resp; },
        (error: HttpErrorResponse) => {console.log(error); }
    );
  }
  // Increament

  // increment(product, qty = 1) {
  //   this.productService.updateCartQuantity(product, qty);
  // }
  // // Decrement
  // public counter: number = 1;
  //
  // decrement(cartItemquantity: number) {
  //   //this.productService.updateCartQuantity(product, qty);
  //   if ( cartItemquantity > 1) {
  //     this.counter--;
  //   }
  //
  //
  // }

  decrementQuantity(cartItem: CartItem) {
    if (cartItem.quantity > 1) {
      cartItem.quantity--;
    }
  }

  incrementQuantity(cartItem: CartItem) {
    if (cartItem.quantity < cartItem.product.quantity) {
      cartItem.quantity++;
    }
  }



  public removeItemCART(id: number) {
    this.productService.removeCartItemm(id).subscribe(
        (response) => {
          this.getCartItemsWithProducts();
        }
    );

  }

  getCartDetails(){
    this.productService.getCartDetails().subscribe(( response : any[]) => {console.log(response);
          this.cart = response;
        },
        (error) => {console.log(error); }
    );
  }


  // public getAllStores(){
  //   this.storeservice.getAllStores()
  //       .pipe(
  //           map((x: Store[], i) => x.map((store: Store) => this.imageProcessingService.createImages(store)))
  //       )
  //       .subscribe(
  //           (resp: Store[]) => {console.log(resp); this.storeList = resp; },
  //           (error: HttpErrorResponse) => {console.log(error); }
  //       );
  // }



}
