import { Component, OnInit, Input } from '@angular/core';
import { NewProductSlider } from '../../../data/slider';
import { Product } from '../../../classes/product';
import { ProductService } from '../../../services/product.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-box-vertical-slider',
  templateUrl: './product-box-vertical-slider.component.html',
  styleUrls: ['./product-box-vertical-slider.component.scss']
})
export class ProductBoxVerticalSliderComponent implements OnInit {
  @Input() product: Product;
  @Input() title: string = 'New Product'; // Default
  @Input() type: string = 'fashion'; // Default Fashion

  public products : Product[] = [];

  public NewProductSliderConfig: any = NewProductSlider;

  constructor(public productService: ProductService, private router: Router) {
    this.productService.getProducts.subscribe(response => 
      this.products = response.filter(item => item.productCategory == this.type)
    );
  }
  showProductDetails(productID) {
    this.router.navigate(['shop/product/left/sidebar/', {productId: productID}]);
    console.log(this.product);
  }
  ngOnInit(): void {
    this.productService.getProducts.subscribe(response =>
        this.products = response.filter(item => item.productCategory == this.type)
    );
  }

}
