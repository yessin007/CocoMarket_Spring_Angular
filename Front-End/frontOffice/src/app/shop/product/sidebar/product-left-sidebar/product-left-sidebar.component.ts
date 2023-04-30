import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDetailsMainSlider, ProductDetailsThumbSlider } from '../../../../shared/data/slider';
import { Product } from '../../../../shared/classes/product';
import {Review} from '../../../../shared/classes/review';
import {CartService} from '../../../../services/cart.service';
import {SizeModalComponent} from '../../../../shared/components/modal/size-modal/size-modal.component';
import {ProductService} from '../../../../shared/services/product.service';
import {User} from '../../../../shared/models/User';



@Component({
  selector: 'app-product-left-sidebar',
  templateUrl: './product-left-sidebar.component.html',
  styleUrls: ['./product-left-sidebar.component.scss']
})
export class ProductLeftSidebarComponent implements OnInit {

  public product: Product = {};
  public review: Review = {};
  user: User = new User();
  public counter = 1;
  public activeSlide: any = 0;
  public selectedSize: any;
  public mobileSidebar = false;
  public liked;
  public disliked;
  rating = 3;
  starCount = 5;
  public reviews: Review[] = [];
  public active = 1;


  @ViewChild('sizeChart') SizeChart: SizeModalComponent;

  public ProductDetailsMainSliderConfig: any = ProductDetailsMainSlider;
  public ProductDetailsThumbConfig: any = ProductDetailsThumbSlider;

  constructor(private route: ActivatedRoute, private router: Router, private cartService: CartService,
              public productService: ProductService) {
  }

  ngOnInit(): void {
    this.product = this.route.snapshot.data.product;
    this.router.navigate(['shop/product/left/sidebar/', {productId: this.product.productId}]);
    this.getAllReviews();
    this.verifyDislikeProduct();
    this.verifyLikeProduct();
    console.log(this.product);
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

  // Add to cart
  async addToCart(productId) {
    console.log(productId);

    this.productService.addToCart(productId).subscribe((response) => {console.log(response); },
        (error) => {console.log(error); });




    // product.quantity = this.counter || 1;
    // const status = await this.productService.addToCart(product);
    // if (status)
    //   this.router.navigate(['/shop/cart']);
  }

  // Buy Now
  async buyNow(product: any) {
    product.quantity = this.counter || 1;
    const status = await this.productService.addToCart(product);
    if (status) {
      this.router.navigate(['/shop/checkout']);
    }
  }

  // Add to Wishlist
  addToWishlist(product: Product) {
    this.productService.likeProduct(product.productId).subscribe((resp) => {
      console.log('like added successfully'); });
    this.productService.addToWishlist(product);
    this.router.navigateByUrl('/home/fashion', { skipLocationChange: true }).then(() => {
      this.router.navigate(['shop/product/left/sidebar/', {productId: this.product.productId}]);
    });
  }
  dislikeProduct(product: Product) {
    this.productService.disLikeProduct(product.productId).subscribe((resp) => {
      console.log('dislike product successfully'); });
    this.router.navigateByUrl('/home/fashion', { skipLocationChange: true }).then(() => {
      this.router.navigate(['shop/product/left/sidebar/', {productId: this.product.productId}]);
    });
  }
  // Toggle Mobile Sidebar
  toggleMobileSidebar() {
    this.mobileSidebar = !this.mobileSidebar;
  }

}
