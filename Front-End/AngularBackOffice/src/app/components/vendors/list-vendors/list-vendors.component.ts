import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import { vendorsDB } from '../../../shared/tables/vendor-list';
import {Store} from '../../../models/store';
import {TableService} from '../../../shared/service/table.service';
import {Router} from '@angular/router';
import {NgbdSortableHeader} from '../../../shared/directives/NgbdSortableHeader';
import {SortEvent} from '../../../shared/directives/shorting.directive';
import {StoreService} from '../../../services/store/store.service';
import {HttpErrorResponse} from '@angular/common/http';
import {ImageProceesingsService} from '../../../services/img/image-proceesings.service';
import {map} from 'rxjs/operators';


@Component({
  selector: 'app-list-vendors',
  templateUrl: './list-vendors.component.html',
  styleUrls: ['./list-vendors.component.scss']
})
export class ListVendorsComponent implements OnInit {
  public storeList: Store[] = [];

  constructor( private route: Router  , private storeservice: StoreService , private  imageProcessingService: ImageProceesingsService,  ) {
  }


  ngOnInit() {
    this.getAllStores();

  }
  public  deleteStore(storeId) {
    this.storeservice.deleteStore(storeId).subscribe(
        (resp) => {
          console.log(resp);
          this.getAllStores();
        },
        (err: HttpErrorResponse) => {
          console.log(err);
        }
    );
  }


    showStoreDetails(storeId) {
        this.route.navigate(['/vendors/store-detail', {storeId}]);
    }
  public editStoreDetails(storeId) {
    this.route.navigate(['/vendors/create-vendors', { storeId}]);
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
