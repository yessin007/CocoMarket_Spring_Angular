import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDetailsMainSlider, ProductDetailsThumbSlider } from '../../../../shared/data/slider';

import {Review} from "../../../../shared/classes/review";
import {CartService} from "../../../../services/cart.service";
import {SizeModalComponent} from "../../../../shared/components/modal/size-modal/size-modal.component";
import {ProductService} from "../../../../shared/services/product.service";
import {NgForm} from "@angular/forms";
import {ProductCart} from "../../../../shared/classes/productcart";
//import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {FileHandle, Product} from '../../../../shared/classes/product';
import {CartItem} from "../../../../shared/classes/CartItem";



@Component({
  selector: 'app-product-left-sidebar',
  templateUrl: './product-left-sidebar.component.html',
  styleUrls: ['./product-left-sidebar.component.scss']
})
export class ProductLeftSidebarComponent implements OnInit {

  public product: Product = {};
  public productCart: ProductCart = {};
  public review: Review = {};
  public counter: number = 1;
  public activeSlide: any = 0;
  public selectedSize: any;
  public mobileSidebar: boolean = false;

  public cartItems: CartItem[] = [];

  rating:number = 3;
  starCount:number = 5;
  public active = 1;

  files: File[] = [];
  array: FileHandle[] = [];
  //public Editor = ClassicEditor;


  @ViewChild("sizeChart") SizeChart: SizeModalComponent;

  public ProductDetailsMainSliderConfig: any = ProductDetailsMainSlider;
  public ProductDetailsThumbConfig: any = ProductDetailsThumbSlider;

  constructor(private route: ActivatedRoute, private router: Router, private cartService: CartService,
              public productService: ProductService) {
  }

  addProductToCartItem(cartId, productId, quantity){
    this.productService.addProductToCartItem(cartId, productId, quantity).subscribe();
  }

  ngOnInit(): void {
    this.product = this.route.snapshot.data.product;
    console.log(this.product);
  }
  reload(productID){
    this.router.navigate(['shop/product/left/sidebar/', {productId: productID}]);
  }
  // Get Product Color
  Color(variants) {
    const uniqColor = []
    for (let i = 0; i < Object.keys(variants).length; i++) {
      if (uniqColor.indexOf(variants[i].color) === -1 && variants[i].color) {
        uniqColor.push(variants[i].color)
      }
    }
    return uniqColor
  }

  // Get Product Size
  Size(variants) {
    const uniqSize = []
    for (let i = 0; i < Object.keys(variants).length; i++) {
      if (uniqSize.indexOf(variants[i].size) === -1 && variants[i].size) {
        uniqSize.push(variants[i].size)
      }
    }
    return uniqSize
  }
  reviewProduct(review: Review){
    this.productService.reviewProduct(review, this.product.productId).subscribe((product: Product) => {
          console.log('review added successfully', product);
          // Reset the form
        },
        (error) => {
          console.error('Failed to add review', error);
        });
  }

  selectSize(size) {
    this.selectedSize = size;
  }

  // Increament
  increment() {
    this.counter++;
  }

  // Decrement
  decrement() {
    if (this.counter > 1) this.counter--;
  }

  // Add to cart
  async addToCart(productId) {
    console.log(productId);
    this.productService.addToCart(productId).subscribe((response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        });
  }

      prepareFormData(product: ProductCart) : FormData{
      const formData = new FormData();

      formData.append('product', new Blob([JSON.stringify(product)], {type: 'application/json'}));

      for (let i = 0; i < product.image.length ; i++) {
        formData.append('imageFile', product.image[i].filefile, product.image[i].filefile.name);
      }
      return formData;
    }

    onSubmitCart(product: ProductCart) {

      this.productService.addProductCart(this.productCart).subscribe(
          (productcart: ProductCart) => {
            console.log('Product added successfully', productcart);
            // Reset the form
            this.productCart = new ProductCart();
          },
          (error) => {
            console.error('Failed to add product to cart', error);
          }
      );

    }
  // onSubmit() {
  //   console.log(this.currentToken);
  //   this.orderService.addOrder( this.order ).subscribe((order: Order) => {console.log('Order added successfully', order);
  //         this.order = new Order(); } ,
  //       (error) => { console.error('Failed to add Order', error); }
  //   );
  // }




    // product.quantity = this.counter || 1;
    // const status = await this.productService.addToCart(product);
    // if (status)
    //   this.router.navigate(['/shop/cart']);


  // Buy Now
  async buyNow(product: any) {
    product.quantity = this.counter || 1;
    const status = await this.productService.addToCart(product);
    if (status)
      this.router.navigate(['/shop/checkout']);
  }

  // Add to Wishlist
  addToWishlist(product: any) {
    this.productService.addToWishlist(product);
  }

  // Toggle Mobile Sidebar
  toggleMobileSidebar() {
    this.mobileSidebar = !this.mobileSidebar;
  }

}
