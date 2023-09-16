import { Component, OnInit, OnDestroy } from '@angular/core';
import { Product } from '../../shared/classes/product';
import { ProductService } from '../../shared/services/product.service';
import {ImageProcessingService} from "../../shared/services/image-processing.service";
import {map} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";
import {Store} from "../../shared/classes/store";
import {StoreService} from "../../shared/services/store/store.service";

@Component({
  selector: 'app-electronics',
  templateUrl: './electronics.component.html',
  styleUrls: ['./electronics.component.scss']
})
export class ElectronicsComponent implements OnInit, OnDestroy {

  constructor(public productService: ProductService, private imageProcessingService: ImageProcessingService, private route: ActivatedRoute
              // tslint:disable-next-line:align
              , private router: Router, private storeService: StoreService) {
    this.Initialization();
  }
  public themeLogo; // Change Logo

  public products: Product[] = [];
  public productCollections: any[] = [];
  public active;
  public product;
  public store: Store = new Store();

  // Collection banner
  public collections = [{
    image: 'assets/electronics/kit.PNG',
    save: '10% off',
    title: 'speaker'
  }, {
    image: 'assets/electronics/headset.JPG',
    save: '10% off',
    title: 'earplug'
  },
  {
    image: 'assets/electronics/speaker.JPG',
    save: '10% off',
    title: 'best deal'
  }]
  public Initialization() {
    this.store = this.route.snapshot.data.store;
    console.log('store Id: ' + this.store.storeId);
    this.storeService.getProductsByStore(this.store.storeId).pipe(
        map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
    )
        .subscribe((response) => {
          console.log(response);
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
    document.documentElement.style.setProperty('--theme-deafult', '#f5c000');
    this.store = this.route.snapshot.data.store;
    this.Initialization();
    this.themeLogo = this.store.storeImages[0].url;
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
