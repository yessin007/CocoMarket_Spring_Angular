import {Component, OnInit} from '@angular/core';
import {Product} from '../../classes/product';
import {ProductService} from '../../services/product.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  public products: Product[] = [];
  public collapse: boolean = true;
  public productCollections: any[] = [];
  constructor(public productService: ProductService) { 
    this.productService.getProducts.subscribe(product => this.products = product);
  }

  ngOnInit(): void {
   // this.productService.getProducts.subscribe(product => this.products = product);
    this.Initialization();
    console.log(this.productCollections);
  }

  get filterbyCategory() {
    return [...new Set(this.products.map(product => product.productCategory))];
  }
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
    });
  }
}
