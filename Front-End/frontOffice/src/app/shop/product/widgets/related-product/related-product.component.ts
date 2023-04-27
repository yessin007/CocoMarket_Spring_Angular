import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../../../../shared/classes/product';
import { ProductService } from '../../../../shared/services/product.service';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-related-product',
  templateUrl: './related-product.component.html',
  styleUrls: ['./related-product.component.scss']
})
export class RelatedProductComponent implements OnInit {
  
  @Input() type: string

  @Input() product: Product = {}

  public products: Product[] = [];

  constructor(public productService: ProductService, private router: Router) {
    this.productService.getProducts.subscribe(response => 
      this.products = response.filter(item => item.productCategory == this.type)
    );
  }
  showProductDetails(productID) {
    this.router.navigate(['shop/product/left/sidebar/', {productId: productID}]);
  }
  ngOnInit(): void {
    this.productService.getProducts.subscribe(response =>
        this.products = response.filter(item => item.productCategory == this.type)
    );
  }

}
