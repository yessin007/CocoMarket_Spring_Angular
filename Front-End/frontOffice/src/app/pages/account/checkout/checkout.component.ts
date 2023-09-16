import { Component, Injectable, OnInit } from '@angular/core';
import { GetCurrentLocation } from '../../../shared/services/GetCurrentLocation';
import { AddDelivery } from '../../../shared/services/AddDelivery';
import {Deliveries} from '../../../shared/services/Deliveries';
import { Product } from '../../../shared/classes/product';
import { ProductService } from '../../../shared/services/product.service';
import { OrderService } from '../../../shared/services/order.service';
import {CartItem} from '../../../shared/classes/CartItem';
import {Cart} from '../../../shared/classes/cart';
import {Order1} from '../../../shared/classes/order1';
import {SmsPojo} from '../../../shared/classes/SmsPojo';
import {ImageProcessingService} from '../../../shared/services/image-processing.service';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from '@angular/forms';
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
  providers: [Deliveries, AddDelivery]
})
export class CheckoutComponent implements OnInit {

  public payment = 'Stripe';
  public amount: any;


  public cartItems: CartItem[] = [];

  public order1: Order1 = new Order1();
  public smsPojo: SmsPojo = new SmsPojo();

  cart: Cart[] = [];
  public cartId = 1;

  public products: Product[] = [];

  public checkoutForm: UntypedFormGroup;
  lat: number;
  lng: number;
  delivery: Deliveries = new Deliveries();

  constructor(private fb: UntypedFormBuilder, private getCurrentLocationn: GetCurrentLocation, private addDelivery: AddDelivery,
              public productService: ProductService, private orderService: OrderService,
              private imageProcessingService: ImageProcessingService) {
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
  isContentHidden = false;

  ngOnInit() {
    //this.initConfig();

    this.getCartItemsWithProducts();
    this.productService.cartItems.subscribe(response => this.products = response);

  }


  public get getTotal(): number {
    let totalAmount = 0;
    for (const cartItem of this.cartItems) {
      let price = cartItem.product.price;
      if (cartItem.product.discount) {
        price = (cartItem.product.price) * (1 - cartItem.product.discount / 100);
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

  getCurrentLocation() {
    this.getCurrentLocationn.getCurrentLocation().then((location) => {
      this.lat = location.coords.latitude;
      this.lng = location.coords.longitude;
      console.log(`lat: ${this.lat}, lng: ${this.lng}`);
    }).catch((err) => {
      console.log(err);
    });
  }
  hideContent() {
    this.isContentHidden = !this.isContentHidden;

  }

  onSubmit(delivery, order1) {
    this.addDelivery.addDeliveryWithLocation(delivery, this.lat, this.lng).subscribe((resp) => {
      console.log(resp);
    });

    this.productService.addOrder( order1 ).subscribe((resp)=>{console.log(resp)});

  }
}
