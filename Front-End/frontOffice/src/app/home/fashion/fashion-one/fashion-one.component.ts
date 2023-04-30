import { Component, OnInit } from '@angular/core';
import { ProductSlider } from '../../../shared/data/slider';
import { Product } from '../../../shared/classes/product';
import { ProductService } from '../../../shared/services/product.service';
import {ImageProcessingService} from "../../../shared/services/image-processing.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-fashion-one',
  templateUrl: './fashion-one.component.html',
  styleUrls: ['./fashion-one.component.scss']
})
export class FashionOneComponent implements OnInit {
  public products: Product[] = [];
  public productCollections: any[] = [];
  public active;
  public product;
  constructor(public productService: ProductService, private imageProcessingService: ImageProcessingService, private route: ActivatedRoute
              // tslint:disable-next-line:align
      , private router: Router) {
  }

  public ProductSliderConfig: any = ProductSlider;

  public sliders = [{
    title: 'welcome to electronics',
    subTitle: 'Find Your Best Products With COCO',
    image: 'assets/images/slider/cee.png'
  }, {
    title: 'welcome to fashion',
    subTitle: 'You Are The Style',
    image: 'assets/images/slider/cfff.png'
  }]

  // Collection banner
  public collections = [{
    image: 'assets/images/wcc.png',
    save: '',
    title: ''
  }, {
    image: 'assets/images/wccc.png',
    save: '',
    title: ''
  }];

  // Blog
  public blog = [{
    image: 'assets/images/blog/1.jpg',
    date: '25 January 2018',
    title: 'Lorem ipsum dolor sit consectetur adipiscing elit,',
    by: 'John Dio'
  }, {
    image: 'assets/images/blog/2.jpg',
    date: '26 January 2018',
    title: 'Lorem ipsum dolor sit consectetur adipiscing elit,',
    by: 'John Dio'
  }, {
    image: 'assets/images/blog/3.jpg',
    date: '27 January 2018',
    title: 'Lorem ipsum dolor sit consectetur adipiscing elit,',
    by: 'John Dio'
  }, {
    image: 'assets/images/blog/4.jpg',
    date: '28 January 2018',
    title: 'Lorem ipsum dolor sit consectetur adipiscing elit,',
    by: 'John Dio'
  }];

  // Logo
  public logo = [{
    image: 'assets/images/logos/1.png',
  }, {
    image: 'assets/images/logos/2.png',
  }, {
    image: 'assets/images/logos/3.png',
  }, {
    image: 'assets/images/logos/4.png',
  }, {
    image: 'assets/images/logos/5.png',
  }, {
    image: 'assets/images/logos/6.png',
  }, {
    image: 'assets/images/logos/7.png',
  }, {
    image: 'assets/images/logos/8.png',
  }];

  ngOnInit(): void {
    this.Initialization();
  }

  // Product Tab collection
  public getCollectionProducts(collection) {
    return this.products.filter((item) => {
      if (item.productCategory === collection) {
        return item;
      }
    });
  }
  public Initialization() {
    this.productService.getProducts.subscribe(response => {
      // tslint:disable-next-line:triple-equals
      this.products = response/*.filter(item => item.productCategory == 'electronics')*/;
      // Get Product Collection
      this.products.filter((item) => {
        item.collection.filter((collection) => {
          const index = this.productCollections.indexOf(item.productCategory);
          if (index === -1) { this.productCollections.push(item.productCategory); }
        });
      });
      this.active = this.productCollections[0];
    });
  }
  showProductDetails(productID) {
    this.router.navigate(['/shop/product/left/sidebar/', {productId: productID}]);
  }

}
