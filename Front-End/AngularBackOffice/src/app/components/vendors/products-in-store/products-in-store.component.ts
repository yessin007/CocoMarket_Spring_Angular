import {Component, OnInit} from '@angular/core';
import {StoreService} from "../../../services/store/store.service";
import {Store} from "../../../models/store";
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../../models/product";

@Component({
  selector: 'app-products-in-store',
  templateUrl: './products-in-store.component.html',
  styleUrls: ['./products-in-store.component.scss']
})
export class ProductsInStoreComponent implements OnInit{
  store: Store;
  // tslint:disable-next-line:ban-types
  public storeProducts: Object = [];
  private storeId: number;
  products: Product[] = [];

  constructor( private  storeservice : StoreService, private activatedRoute: ActivatedRoute){

  }
  ngOnInit(): void {
    // @ts-ignore
    this.fetchProducts();
  }
  // getProductsByStore(): void {
  //   this.storeservice.getProductsByStore(this.storeId)
  //       .subscribe(products => this.products = products);
  // }
  fetchProducts(storeId: number) {
    this.storeservice.getProductsByStore(storeId).subscribe(
        (data) => {
console.log('aaa');        },
        (error) => {
console.log('bbb');        }
    );
  }
}
