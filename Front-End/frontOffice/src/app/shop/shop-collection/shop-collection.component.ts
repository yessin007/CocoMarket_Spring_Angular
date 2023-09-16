import {Component, OnInit} from '@angular/core';
import {Product} from '../../shared/classes/product';
import {Store} from '../../shared/classes/store';
import {ActivatedRoute, Router} from '@angular/router';
import {ViewportScroller} from '@angular/common';
import {ProductService} from '../../shared/services/product.service';
import {StoreService} from '../../shared/services/store/store.service';
import {ImageProceesingsService} from '../../shared/services/img/image-proceesings.service';
import {map} from 'rxjs/operators';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-shop-collection',
  templateUrl: './shop-collection.component.html',
  styleUrls: ['./shop-collection.component.scss']
})
export class ShopCollectionComponent implements OnInit{

  public grid = 'col-xl-3 col-md-6';
  public layoutView = 'grid-view';
  public products: Product[] = [];
  public pageNo = 1;
  public paginate: any = {}; // Pagination use only
  public sortBy: string; // Sorting Order
  public storeList: Store[] = [];

  constructor( private rr: Router  , private route: ActivatedRoute, private router: Router, private viewScroller: ViewportScroller, public productService: ProductService , private storeservice: StoreService, private  imageProcessingService: ImageProceesingsService) {
    // Get Query params..
    this.route.queryParams.subscribe(params => {

      this.sortBy = params.sortBy ? params.sortBy : 'ascending';
      this.pageNo = params.page ? params.page : this.pageNo;

      // Get Filtered Products..
      this.productService.getProducts.subscribe(response => {
        // Sorting Filter
        this.products = this.productService.sortProducts(response, this.sortBy);
        // Paginate Products
        this.paginate = this.productService.getPager(this.products.length, +this.pageNo);     // get paginate object from service
        this.products = this.products.slice(this.paginate.startIndex, this.paginate.endIndex + 1); // get current page of items
      });
    });
  }

  ngOnInit(): void {
    this.getAllStores();
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
