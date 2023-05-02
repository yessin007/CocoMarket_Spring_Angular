import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
// import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';
import { environment } from '../../../environments/environment';
import { Product } from '../../shared/classes/product';
import { ProductService } from '../../shared/services/product.service';
import { OrderService } from '../../shared/services/order.service';
import {CartItem} from '../../shared/classes/CartItem';
import {Cart} from '../../shared/classes/cart';
import {Order1} from '../../shared/classes/order1';
import {SmsPojo} from "../../shared/classes/SmsPojo";
import {ImageProcessingService} from "../../shared/services/image-processing.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  public checkoutForm: UntypedFormGroup;
  public products: Product[] = [];
  // public payPalConfig ? : IPayPalConfig;
  public payment = 'Stripe';
  public amount: any;


  public cartItems: CartItem[] = [];

  public order1: Order1 = new Order1();
  public smsPojo: SmsPojo = new SmsPojo();

  cart: Cart[] = [];
  public cartId: number = 1;


  constructor(private fb: UntypedFormBuilder,
              public productService: ProductService,
              private orderService: OrderService, private imageProcessingService: ImageProcessingService) {
    this.checkoutForm = this.fb.group({
      firstname: ['', [Validators.required, Validators.pattern('[a-zA-Z][a-zA-Z ]+[a-zA-Z]$')]],
      lastname: ['', [Validators.required, Validators.pattern('[a-zA-Z][a-zA-Z ]+[a-zA-Z]$')]],
      phone: ['', [Validators.required, Validators.pattern('[0-9]+')]],
      email: ['', [Validators.required, Validators.email]],
      address: ['', [Validators.required, Validators.maxLength(50)]],
      country: ['', Validators.required],
      town: ['', Validators.required],
      state: ['', Validators.required],
      postalcode: ['', Validators.required]
    });
  }




  ngOnInit(): void {
    //this.productService.cartItems.subscribe(response => this.products = response);
    //this.getTotal.subscribe(amount => this.amount = amount);
    this.initConfig();

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

  getTotalD(){
    let totalAmount = 0;
    for (const cartItem of this.cartItems) {
      let price = cartItem.product.price;
      if (cartItem.product.discount) {
        price = (cartItem.product.price) * (  cartItem.product.discount / 100 );
      }
      totalAmount += price * cartItem.quantity;
    }
    return totalAmount;
  }

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




  // public get getTotal(): Observable<number> {
  //   return this.productService.cartTotalAmount();
  // }

  // Stripe Payment Gateway
  stripeCheckout() {
    let handler = (window as any).StripeCheckout.configure({
      key: environment.stripe_token, // publishble key
      locale: 'auto',
      token: (token: any) => {
        // You can access the token ID with `token.id`.
        // Get the token ID to your server-side code for use.
        this.orderService.createOrder(this.products, this.checkoutForm.value, token.id, this.amount);
      }
    });

    handler.open({
      name: 'Multikart',
      description: 'Online Fashion Store',
      // amount: this.amount * 100
      amount: 100 ,
    });
  }

  // Paypal Payment Gateway
  private initConfig(): void {
    // this.payPalConfig = {
    //     currency: this.productService.Currency.currency,
    //     clientId: environment.paypal_token,
    //     createOrderOnClient: (data) => < ICreateOrderRequest > {
    //       intent: 'CAPTURE',
    //       purchase_units: [{
    //           amount: {
    //             currency_code: this.productService.Currency.currency,
    //             value: this.amount,
    //             breakdown: {
    //                 item_total: {
    //                     currency_code: this.productService.Currency.currency,
    //                     value: this.amount
    //                 }
    //             }
    //           }
    //       }]
    //   },
    //     advanced: {
    //         commit: 'true'
    //     },
    //     style: {
    //         label: 'paypal',
    //         size:  'small', // small | medium | large | responsive
    //         shape: 'rect', // pill | rect
    //     },
    //     onApprove: (data, actions) => {
    //         this.orderService.createOrder(this.products, this.checkoutForm.value, data.orderID, this.getTotal);
    //         console.log('onApprove - transaction was approved, but not authorized', data, actions);
    //         actions.order.get().then(details => {
    //             console.log('onApprove - you can get full order details inside onApprove: ', details);
    //         });
    //     },
    //     onClientAuthorization: (data) => {
    //         console.log('onClientAuthorization - you should probably inform your server about completed transaction at this point', data);
    //     },
    //     onCancel: (data, actions) => {
    //         console.log('OnCancel', data, actions);
    //     },
    //     onError: err => {
    //         console.log('OnError', err);
    //     },
    //     onClick: (data, actions) => {
    //         console.log('onClick', data, actions);
    //     }
    // };
  }


  onSubmit() {

    this.productService.addOrder( this.order1 ).subscribe(
        (order1: Order1) =>
        {console.log('Order added successfully', order1);
         this.order1 = new Order1(); } ,
        (error) => { console.error('Failed to add Order', error); }
    );
  }


  sms(){
    this.smsPojo.message = 'Felicitation ! Commande Confirmé avec succeés ! :)';
    this.smsPojo.phonenumber = '+21627532414';
    this.productService.sms(this.smsPojo).subscribe();
  }
}
