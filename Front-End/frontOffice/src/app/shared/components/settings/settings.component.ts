import {Component, OnInit, Injectable, PLATFORM_ID, Inject, OnChanges} from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Observable } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../classes/product';
import {ActivatedRoute, Router} from '@angular/router';
import {map} from 'rxjs/operators';
import {ImageProcessingService} from '../../services/image-processing.service';
import {response} from 'express';
import {CartItem} from '../../classes/CartItem';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss']
})
export class SettingsComponent implements OnInit, OnChanges {

  public products: Product[] = [];

  public cartItems: CartItem[] = [];

  public p: Product = {};
  public search = false;
  public cartId = 1;

  public indexCart;


  public languages = [{
    name: 'English',
    code: 'en'
  }, {
    name: 'French',
    code: 'fr'
  }];

  public currencies = [{
    name: 'Euro',
    currency: 'EUR',
    price: 0.90 // price of euro
  }, {
    name: 'Rupees',
    currency: 'INR',
    price: 70.93 // price of inr
  }, {
    name: 'Pound',
    currency: 'GBP',
    price: 0.78 // price of euro
  }, {
    name: 'Dollar',
    currency: 'USD',
    price: 1 // price of usd
  }];

  constructor(@Inject(PLATFORM_ID) private platformId: Object,
              private translate: TranslateService,
              public productService: ProductService, private route: ActivatedRoute, private imageProcessingService: ImageProcessingService,
              private router: Router) {

  }
  public searchByKeyWord(searchKeyWord){
    console.log(searchKeyWord);
    this.products = [];
    this.getProductsByKeyWord(searchKeyWord);
  }
  getProductsByKeyWord(searchKey: string = ''){
    this.productService.searchByKeyWord(searchKey).pipe(
        map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
    ).subscribe((resp: Product[]) => {
      console.log(resp); this.products = resp; });
  }


  ngOnInit(): void {
    // this.p = this.route.snapshot.data.product;
    console.log(this.p);

    this.getCartItemsWithProducts();

    this.indexCart = this.productService.getCartIndex().subscribe();

  }

  ngOnChanges(): void {
    this.getCartItemsWithProducts();
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
                  this.indexCart = this.cartItems.length ;
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

  searchToggle(){
    this.router.navigate(['pages/search']);
  }

  changeLanguage(code){
    if (isPlatformBrowser(this.platformId)) {
      this.translate.use(code);
    }
  }

  get getTotal(): Observable<number> {
    return this.productService.cartTotalAmount();
  }

  removeItem(product: any) {
    this.productService.removeCartItem(product);
  }

  changeCurrency(currency: any) {
    this.productService.Currency = currency;
  }

}
