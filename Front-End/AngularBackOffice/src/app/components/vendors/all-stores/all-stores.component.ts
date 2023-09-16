import {Component, OnInit} from '@angular/core';
import {vendorsDB} from '../../../shared/tables/vendor-list';
import {StoreService} from '../../../services/store/store.service';
import {map} from 'rxjs/operators';
import {Store} from '../../../models/store';
import {HttpErrorResponse} from '@angular/common/http';
import {ImageProceesingsService} from '../../../services/img/image-proceesings.service';
import {Router} from '@angular/router';
import {response} from 'express';

@Component({
  selector: 'app-all-stores',
  templateUrl: './all-stores.component.html',
  styleUrls: ['./all-stores.component.scss']
})
export class AllStoresComponent  implements OnInit {
  // tslint:disable-next-line:variable-name
  public storeList: Store[] = [];
  public store: Store = new Store();
  constructor( private  storeservice: StoreService, private  imageProcessingService: ImageProceesingsService, private route: Router  ) {
  }
  ngOnInit(): void {
    this.getAllStores();
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
  public SHoxStoreDetails(storeId) {
    this.route.navigate(['/vendors/store-detail', { storeId}]);
  }
  public getStoreDetails(storeId){
    this.storeservice.getStoreDetails(storeId).subscribe((resp) => {this.store = resp; });
  }
  public getStoreProducts(storeID){
      this.route.navigate(['/products/physical/product-list', {storeId: storeID}]);
  }
}
