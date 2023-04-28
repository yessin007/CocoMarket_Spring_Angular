import { Injectable } from '@angular/core';
import {CatalogServiceService} from "../catalogService/catalog-service.service";
import {StoreCatalog} from "../../models/storeCatalog";
import {ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StrCtlgResolverService {

  constructor(private catalogService: CatalogServiceService) { }
  catalog: StoreCatalog = new StoreCatalog();
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<StoreCatalog> {
    const id = route.paramMap.get('catalogId');
    if (id){
      return this.catalogService.getCatalogDetails(id);
    } else {
      return of(this.getCatalogDetails());
    }
  }

  getCatalogDetails(){
    return this.catalog;
  }
}
