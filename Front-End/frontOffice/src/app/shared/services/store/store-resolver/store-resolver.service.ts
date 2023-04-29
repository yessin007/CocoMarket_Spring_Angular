import { Injectable } from '@angular/core';

import {StoreService} from '../store.service';
import {ImageProceesingsService} from '../../img/image-proceesings.service';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';
import {Store} from '../../../models/store';

@Injectable({
  providedIn: 'root'
})
export class StoreResolverService implements Resolve<Store>{

  constructor(private storeService: StoreService, private imageProcessingService: ImageProceesingsService) { }
  store: Store = new Store();
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Store> {
    const id = route.paramMap.get('storeId');
    if (id){
      return this.storeService.getStoreDetails(id).pipe(
          map(p => this.imageProcessingService.createImages(p))
      );
    }else {
      return of(this.getStoreDetails());
    }
  }

  getStoreDetails(){
    return this.store;
  }
}
