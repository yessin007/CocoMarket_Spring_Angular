import { Component, OnInit } from '@angular/core';
import {map} from "rxjs/operators";
import {Product} from "../../shared/classes/product";
import {ProductService} from "../../shared/services/product.service";
import {ImageProcessingService} from "../../shared/services/image-processing.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  public productCollections: any[] = [];
  public aiProductCollections: any[] = [];
  public active;
  public activeA;
  public products: Product[] = [];
  public aiProducts: Product[] = [];
  constructor( public productService: ProductService, private imageProcessingService: ImageProcessingService, private router: Router) { }
  public searchByKeyWord(searchKeyWord){
    console.log(searchKeyWord);
    this.products = [];
    this.getProductsByKeyWord(searchKeyWord);
  }
  getProductsByKeyWord(searchKey: string = ''){
    this.productService.searchByKeyWord(searchKey).pipe(
        map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
    ).subscribe(response => {
      // tslint:disable-next-line:triple-equals
      this.products = response;
      // Get Product Collection
      this.products.filter((item) => {
          const index = this.productCollections.indexOf(item.productCategory);
          if (index === -1) { this.productCollections.push(item.productCategory); }
      });
      this.active = this.productCollections[0];
    });
  }
  AiSearchWithGBT(searchKey: string = ''){
    this.productService.findProductWithGBT3(searchKey).subscribe(response => {
      console.log(response);
      // tslint:disable-next-line:triple-equals no-shadowed-variable
      response.forEach((p) => this.productService.searchByKeyWord(p).pipe(
          map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
      ).subscribe((res) => res.forEach((pl) => this.aiProducts.push(pl)))
      );

      // Get Product Collection
      this.aiProducts.filter((item) => {
        const index = this.aiProductCollections.indexOf(item.productCategory);
        if (index === -1) { this.aiProductCollections.push(item.productCategory); }
      });
      this.activeA = this.aiProductCollections[0];
    });
  }

  public getCollectionProducts(collection) {
    return this.products.filter((item) => {
      if (item.productCategory === collection) {
        return item;
      }
    });
  }
  showProductDetails(productID) {
    this.router.navigate(['/shop/product/left/sidebar/', {productId: productID}]);
  }
  ngOnInit(): void {
  }

}
