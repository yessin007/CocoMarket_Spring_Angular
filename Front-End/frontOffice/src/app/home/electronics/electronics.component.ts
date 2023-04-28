import { Component, OnInit, OnDestroy } from '@angular/core';
import { Product } from '../../shared/classes/product';
import { ProductService } from '../../shared/services/product.service';
import {ImageProcessingService} from "../../shared/services/image-processing.service";
import {map} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-electronics',
  templateUrl: './electronics.component.html',
  styleUrls: ['./electronics.component.scss']
})
export class ElectronicsComponent implements OnInit, OnDestroy {

  constructor(public productService: ProductService, private imageProcessingService: ImageProcessingService, private route: ActivatedRoute
              // tslint:disable-next-line:align
              , private router: Router) {
    this.Initialization();
  }
  public themeLogo = 'assets/images/icon/logo-10.png'; // Change Logo

  public products: Product[] = [];
  public productCollections: any[] = [];
  public active;
  public product;

  // Collection banner
  public collections = [{
    image: 'assets/images/collection/electronics/1.jpg',
    save: '10% off',
    title: 'speaker'
  }, {
    image: 'assets/images/collection/electronics/2.jpg',
    save: '10% off',
    title: 'earplug'
  },
  {
    image: 'assets/images/collection/electronics/3.jpg',
    save: '10% off',
    title: 'best deal'
  }]
  public Initialization() {
    this.productService.getProducts.subscribe(response => {
      // tslint:disable-next-line:triple-equals
      this.products = response.filter(item => item.productCategory == 'electronics');
      // Get Product Collection
      this.products.filter((item) => {
        item.collection.filter((collection) => {
          const index = this.productCollections.indexOf(collection);
          if (index === -1) { this.productCollections.push(collection); }
        });
      });
      this.active = this.productCollections[0];
    });
  }

  ngOnInit(): void {
    // Change color for this layout
    this.Initialization();
    document.documentElement.style.setProperty('--theme-deafult', '#f5c000');
  }

  ngOnDestroy(): void {
    // Remove Color
    document.documentElement.style.removeProperty('--theme-deafult');
  }

  // Product Tab collection
  public getCollectionProducts(collection) {
    return this.products.filter((item) => {
      if (item.collection.find(i => i === collection)) {
        return item
      }
    })
  }
  // change the path
  showProductDetails(productID) {
    this.router.navigate(['/shop/product/left/sidebar/', {productId: productID}]);
  }

}
