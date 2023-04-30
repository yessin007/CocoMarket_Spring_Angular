import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { Product } from '../../../../shared/classes/product';
import { ProductService } from '../../../../shared/services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductLeftSidebarComponent} from '../../sidebar/product-left-sidebar/product-left-sidebar.component';

@Component({
  selector: 'app-related-product',
  templateUrl: './related-product.component.html',
  styleUrls: ['./related-product.component.scss']
})
export class RelatedProductComponent implements OnInit {

  @Input() type: string;

  @Input() product: Product = {};


  public products: Product[] = [];

  constructor(public productService: ProductService, private router: Router, private productLeftSideBar: ProductLeftSidebarComponent) {
    this.productService.getProducts.subscribe(response =>
      this.products = response.filter(item => item.productCategory == this.type)
    );
  }
    refresh(productID){
        this.productService.getProductById(productID).subscribe((resp) => {
            this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
                this.router.navigate(['shop/product/left/sidebar/', {productId: this.product.productId}]);
            }); } );
    }
  showProductDetails(productID) {
    // this.router.navigate(['shop/product/left/sidebar/', {productId: productID}]);
   this.productService.getProductById(this.product.productId).subscribe((resp) => {
     this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
       this.router.navigate(['shop/product/left/sidebar/', {productId: productID}]); });
       }
   );
  }
  ngOnInit(): void {

    this.productService.getProducts.subscribe((response) => {
          this.products = response.filter(item =>
              item.productCategory == this.type
          );
        }
    );
  }

}
