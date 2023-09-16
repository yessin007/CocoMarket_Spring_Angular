import { Component, OnInit } from '@angular/core';
import { productDB } from 'src/app/shared/tables/product-list';
import {map} from "rxjs/operators";
import {Product} from "../../../../models/product";
import {HttpErrorResponse} from "@angular/common/http";
import {TableService} from "../../../../shared/service/table.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ProductService} from "../../../../services/product/product.service";
import {MatDialog} from "@angular/material/dialog";
import {ImageProcessingService} from "../../../../services/image-processing.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Store} from "../../../../models/store";
import {StoreService} from "../../../../services/store/store.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  // tslint:disable-next-line:variable-name
  public product_list: Product[] = [];
  public productByStore: Product[] = [];
  public store: Store = new Store();

  constructor(public service: TableService, private modalService: NgbModal, private productService: ProductService, private imagediag: MatDialog,
              private  imageProcessingService: ImageProcessingService, private route: Router, private activatedRoute: ActivatedRoute, private storeService: StoreService) {
    //this.product_list = productDB.product;
  }

  ngOnInit() {
    this.getAllProducts();
    this.store = this.activatedRoute.snapshot.data.store ;
    this.getPrductsByStore();
    console.log(this.store);
  }
  public getAllProducts(){
    this.productService.getAllProducts()
        .pipe(
            map((x: Product[], i) => x.filter(product => product.productCategory === 'fashion')
                .map((product: Product) => this.imageProcessingService.createImages(product))))
        .subscribe(
            (resp: Product[]) => {console.log(resp); this.product_list = resp; console.log(this.product_list); this.product_list.forEach(
                product => {
                  this.productService.getNumberOfLikesOfProduct(product.productId).subscribe(prod => product.numberOfLikes = prod);
                  this.productService.getAverageLikesOfProduct(product.productId).subscribe(prod => product.avgLikes = prod);
                }
            ); },
            (error: HttpErrorResponse) => {console.log(error); }
        );
  }
  public  deleteProduct(productId) {
    this.productService.deleteProduct(productId).subscribe(
        (resp) => {
          console.log(resp);
          this.getAllProducts();
        },
        (err: HttpErrorResponse) => {
          console.log(err);
        }
    );
  }
  public editProductDetails(productID) {
    this.route.navigate(['/products/physical/add-product', {productId: productID}]);
  }
  public getPrductsByStore(){
      if (this.store.storeId > 0){
          this.storeService.getProductsByStore(this.store.storeId).pipe(
              map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
          )
              .subscribe((resp) => {
              this.productByStore = resp;
          });
      }
  }
  showProductDetails(productID) {
    this.route.navigate(['/products/physical/product-detail', {productId: productID}]);
  }

}
