import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import { vendorsDB } from '../../../shared/tables/vendor-list';
import {Store} from '../../../models/store';
import {TableService} from '../../../shared/service/table.service';
import {Router} from '@angular/router';
import {NgbdSortableHeader} from '../../../shared/directives/NgbdSortableHeader';
import {SortEvent} from '../../../shared/directives/shorting.directive';
import {Observable} from 'rxjs';
import {DigitalCategoryDB} from '../../../shared/tables/digital-category';
import {Product} from '../../../models/product';
import {HttpErrorResponse} from "@angular/common/http";
import {StoreService} from "../../../services/store/store.service";
import {map} from "rxjs/operators";
import {ImageProcessingService} from "../../../services/image-processing.service";
import {ImageProceesingsService} from "../../../services/img/image-proceesings.service";

@Component({
  selector: 'app-list-vendors',
  templateUrl: './list-vendors.component.html',
  styleUrls: ['./list-vendors.component.scss']
})
export class ListVendorsComponent implements OnInit {

  constructor(public service: TableService , private  imageProcessingService: ImageProceesingsService, private route: Router , private storeservice: StoreService) {
    this.vendors = vendorsDB.data;
  }
  public vendors = [];
  stores: Store[] = [];
  tableItem$: Observable<Store[]>;

  public storeList: Store[] = [];
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  public settings = {
    actions: {
      position: 'right'
    },
    columns: {
      vendor: {
        title: 'Vendor',
        type: 'html',
      },
      products: {
        title: 'Products'
      },
      store_name: {
        title: 'Store Name'
      },
      date: {
        title: 'Date'
      },
      balance: {
        title: 'Wallet Balance',
      },
      revenue: {
        title: 'Revenue',
      }
    },
  };

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach((header) => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.service.sortColumn = column;
    this.service.sortDirection = direction;

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
    this.route.navigate(['/vendors/store/store-detail', {storeId}]);
  }
  public editStoreDetails(productID) {
    this.route.navigate(['/vendors/store/create-vendors', {productId: productID}]);
  }
}
