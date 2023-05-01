import { Injectable } from '@angular/core';
import {CatalogServiceService} from '../catalogService/catalog-service.service';
import {StoreCatalog} from '../../models/storeCatalog';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {ImageMalProcessingService} from '../str-cat/imageMal-processing.service';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StrCtlgResolverService implements Resolve<StoreCatalog>{

  constructor(private catalogService: CatalogServiceService, private imageProcessingService: ImageMalProcessingService) { }
  catalog: StoreCatalog = new StoreCatalog();
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<StoreCatalog> {
    const id = route.paramMap.get('catalogId');
    if (id){
      return this.catalogService.getCatalogDetails(id).pipe(map(p => this.imageProcessingService.createImagesMal(p)));
    } else {
      return of(this.getCatalogDetails());
    }
  }

  getCatalogDetails(){
    return this.catalog;
  }
}
