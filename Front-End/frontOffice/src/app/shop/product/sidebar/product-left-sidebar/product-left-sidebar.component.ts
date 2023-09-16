import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDetailsMainSlider, ProductDetailsThumbSlider } from '../../../../shared/data/slider';
import { Product } from '../../../../shared/classes/product';
import {Review} from '../../../../shared/classes/review';
import {CartService} from '../../../../services/cart.service';
import {SizeModalComponent} from '../../../../shared/components/modal/size-modal/size-modal.component';
import {ProductService} from '../../../../shared/services/product.service';
import {User} from '../../../../shared/models/User';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {SubDialogueComponent} from "../../../sub-dialogue/sub-dialogue.component";
import {Subscription} from "../../../../shared/classes/subscription";
import { DatePipe } from '@angular/common';

import {NgForm, UntypedFormGroup} from "@angular/forms";

import {FileHandle} from '../../../../shared/classes/product';
import {CartItem} from "../../../../shared/classes/CartItem";
import {AuthService} from "../../../../shared/services/auth.service";


@Component({
  selector: 'app-product-left-sidebar',
  templateUrl: './product-left-sidebar.component.html',
  styleUrls: ['./product-left-sidebar.component.scss']
})
export class ProductLeftSidebarComponent implements OnInit {

  public checkoutForm:  UntypedFormGroup;

  public cartItems: CartItem[] = [];
  public product: Product = {};
  public review: Review = {};
  public subsciption: Subscription = {};
  user: User = new User();
  public counter = 1;
  public activeSlide: any = 0;
  public selectedSize: any;
  public mobileSidebar = false;
  public liked = false;
  public disliked = false;
  public getUSDate;
  rating = 3;
  starCount = 5;
  public reviews: Review[] = [];
  public active = 1;
  public collapse = true;
  public productCollections: any[] = [];
  verify = true;
  files: File[] = [];
  array: FileHandle[] = [];


  @ViewChild('sizeChart') SizeChart: SizeModalComponent;

  public ProductDetailsMainSliderConfig: any = ProductDetailsMainSlider;
  public ProductDetailsThumbConfig: any = ProductDetailsThumbSlider;
  currentUser: User = new User();
  public id ;
  constructor(private route: ActivatedRoute, private router: Router, private cartService: CartService,
              public productService: ProductService, public dialog: MatDialog, private authService: AuthService) {
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data;
      this.id = this.currentUser.id;
    });
  }

  addProductToCartItem(cartId, productId, quantity){
    if (this.verify === true) {
    this.productService.addProductToCartItem(cartId, productId, quantity).subscribe();  }

  else{
  this.router.navigate(['/pages/login']);
}
  }
  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '800px';
    dialogConfig.position = {
      left: '30%',
      top: '12%'
    };
    dialogConfig.data = { product: this.product, sub: this.subsciption };
    const dialogRef = this.dialog.open(SubDialogueComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
  ngOnInit(): void {
    this.product = this.route.snapshot.data.product;
    this.router.navigate(['shop/product/left/sidebar/', {productId: this.product.productId}]);
    this.getAllReviews();
    this.verifyDislikeProduct();
    this.verifyLikeProduct();
    console.log(this.product);
    this.findSub();
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data;
      this.id = this.currentUser.id;
    });
    if (this.currentUser == null){
      this.verify = false;
    }
   // console.log(this.subsciption);
    //this.getDateUS();
  }
  shake() {
    document.body.classList.add('shake');
    setTimeout(() => {
      document.body.classList.remove('shake');
    }, 1000);
  }

  glow() {
    document.querySelector('button').classList.add('glow');
  }

  unglow() {
    document.querySelector('button').classList.remove('glow');
  }
  public getDateUS(){
    if (this.subsciption != null){
     const datePipe = new DatePipe('en-US');
     this.getUSDate = datePipe.transform(this.subsciption.dateEndOfSubscription, 'MMM dd yyyy');
     console.log(this.getUSDate);
   }
  }

  refresh(product){
   this.product = product;
  }
  reload(productID){
    this.router.navigate(['shop/product/left/sidebar/', {productId: productID}]);
  }
  // Get Product Color
  Color(variants) {
    const uniqColor = [];
    for (let i = 0; i < Object.keys(variants).length; i++) {
      if (uniqColor.indexOf(variants[i].color) === -1 && variants[i].color) {
        uniqColor.push(variants[i].color);
      }
    }
    return uniqColor;
  }

  // Get Product Size
  Size(variants) {
    const uniqSize = [];
    for (let i = 0; i < Object.keys(variants).length; i++) {
      if (uniqSize.indexOf(variants[i].size) === -1 && variants[i].size) {
        uniqSize.push(variants[i].size);
      }
    }
    return uniqSize;
  }
  verifyDislikeProduct(){
    this.productService.verifyDisikeProduct(this.product.productId).subscribe((resp) => {
      this.disliked = resp;
     // console.log(resp);
      console.log(this.disliked)
    }) ;
  }
  verifyLikeProduct(){
    this.productService.verifyLikeProduct(this.product.productId).subscribe((resp) => {
      this.liked = resp;
    //  console.log(resp);
      console.log(this.liked);
    }) ;
  }
  reviewProduct(review: Review){
    if (this.verify === true){
    this.productService.reviewProduct(review, this.product.productId).subscribe((product: Product) => {
          console.log('review added successfully', product);
          this.router.navigateByUrl('/home/fashion', { skipLocationChange: true }).then(() => {
            this.router.navigate(['shop/product/left/sidebar/', {productId: this.product.productId}]);
          });
          // Reset the form
        },
        (error) => {
          console.error('Failed to add review', error);
        });  }

  else{
  this.router.navigate(['/pages/login']);
}
  }

  selectSize(size) {
    this.selectedSize = size;
  }
  public getAllReviews(){
    this.productService.getAllReviews(this.product.productId).subscribe((resp) => {
      this.reviews = resp;
      this.reviews.forEach(review => {
      this.productService.getUserByReview(review.reviewId).subscribe(user => review.user = user);
    });
    });
  }
  // Increament
  increment() {
    this.counter++;
  }

  // Decrement
  decrement() {
    if (this.counter > 1) { this.counter--; }
  }
  findSub() {
    if (this.verify === true){
    this.productService.findSub(this.product.productId).subscribe((resp) => {
       this.subsciption = resp; console.log(this.subsciption);
       const datePipe = new DatePipe('en-US');
       this.getUSDate = datePipe.transform(this.subsciption.dateEndOfSubscription, 'MMM dd yyyy');
       console.log(this.getUSDate); }); }

    else{
    this.router.navigate(['/pages/login']);
}
  }

  // Add to cart
  async addToCart(productId) {
    if (this.verify === true){
    console.log(productId);

    this.productService.addToCart(productId).subscribe((response) => {console.log(response); },
        (error) => {console.log(error); }); }

    else{
      this.router.navigate(['/pages/login']);
    }



    // product.quantity = this.counter || 1;
    // const status = await this.productService.addToCart(product);
    // if (status)
    //   this.router.navigate(['/shop/cart']);
  }

  // Buy Now
  async buyNow(product: any) {
    if (this.verify === true){
    product.quantity = this.counter || 1;
    const status = await this.productService.addToCart(product);
    if (status) {
      this.router.navigate(['/shop/checkout']);
    }}
    else{
      this.router.navigate(['/pages/login']);
    }
  }

  // Add to Wishlist
  addToWishlist(product: Product) {
    // tslint:disable-next-line:triple-equals
   if (this.verify === true){
     this.productService.likeProduct(product.productId).subscribe((resp) => {
       console.log('like added successfully'); });
     this.productService.addToWishlist(product);
     this.router.navigateByUrl('/home/fashion', { skipLocationChange: true }).then(() => {
       this.router.navigate(['shop/product/left/sidebar/', {productId: this.product.productId}]);
     });
   }
   else {
     this.router.navigate(['/pages/login']);
   }
  }
  dislikeProduct(product: Product) {
    if (this.verify === true){
    this.productService.disLikeProduct(product.productId).subscribe((resp) => {
      console.log('dislike product successfully'); });
    this.router.navigateByUrl('/home/fashion', { skipLocationChange: true }).then(() => {
      this.router.navigate(['shop/product/left/sidebar/', {productId: this.product.productId}]);
    });
    }
    else {
      this.router.navigate(['/pages/login']);
    }
  }
  // Toggle Mobile Sidebar
  toggleMobileSidebar() {
    this.mobileSidebar = !this.mobileSidebar;
  }

}
