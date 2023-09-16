import { Component, OnInit } from '@angular/core';
import {map} from "rxjs/operators";
import {Store} from "../../shared/classes/store";
import {HttpErrorResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {ViewportScroller} from "@angular/common";
import {ProductService} from "../../shared/services/product.service";
import {StoreService} from "../../shared/services/store/store.service";
import {ImageProceesingsService} from "../../shared/services/img/image-proceesings.service";

@Component({
  selector: 'app-collection',
  templateUrl: './collection.component.html',
  styleUrls: ['./collection.component.scss']
})
export class CollectionComponent implements OnInit {
  public  storeList: Store[] = [];

  constructor(private rr: Router  , private route: ActivatedRoute, private router: Router, private viewScroller: ViewportScroller,
              // tslint:disable-next-line:max-line-length
              public productService: ProductService , private storeservice: StoreService, private  imageProcessingService: ImageProceesingsService) { }

  ngOnInit(): void {
    this.getAllStores();
  }
  gotoElectronicStoreProducts(id){
    this.rr.navigate(['/home/electronics', {storeId: id}]);
  }
  gotoFashionStoreProducts(id){
    this.rr.navigate(['/home/fashion-2', {storeId: id}]);
  }
  showPostes(storeID) {
    this.rr.navigate(['/shop/collection/post-store', {storeId: storeID}]);
  }
  showStoreDetails(storeID) {
    this.rr.navigate(['/shop/collection/post-store', {storeId: storeID}]);
  }
  public getAllStores(){
    this.storeservice.getAllStores()
        .pipe(
            map((x: Store[], i) => x.map((store: Store) => this.imageProcessingService.createImages(store)))
        )
        .subscribe(
            (resp: Store[]) => {console.log(resp); this.storeList = resp; },
            (error: HttpErrorResponse) => {console.log(error); }
        );
  }




}
