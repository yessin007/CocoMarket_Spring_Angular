import { Component, OnInit } from '@angular/core';
import {Store} from '../shared/models/store';
import {HttpErrorResponse} from '@angular/common/http';
import {StoreService} from '../shared/services/store/store.service';
import {ImageProceesingsService} from '../shared/services/img/image-proceesings.service';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit {
  public storeList: Store[] = [];

  constructor(private  storeservice: StoreService, private  imageProcessingService: ImageProceesingsService) { }

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

}
