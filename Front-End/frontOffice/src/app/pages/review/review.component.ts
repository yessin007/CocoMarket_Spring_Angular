import {Component, Input, OnInit} from '@angular/core';
import {ProductService} from "../../shared/services/product.service";
import {Review} from "../../shared/classes/review";
import {User} from "../../shared/models/User";
import {Product} from "../../shared/classes/product";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss']
})
export class ReviewComponent implements OnInit {
  @Input() product: Product;
  @Input() reviews: Review[];
  user: User = new User();
  constructor(private productService: ProductService, private route: ActivatedRoute ) { }
  public getUserFullNameByReview(reviewId){
    this.productService.getUserByReview(reviewId).subscribe((res) => {this.user = res; });
    return this.user.name;
  }
  public getAllReviews(){
    this.productService.getAllReviews(this.product.productId).subscribe((resp) => {this.reviews = resp; });
  }
  ngOnInit(): void {
  }

}
