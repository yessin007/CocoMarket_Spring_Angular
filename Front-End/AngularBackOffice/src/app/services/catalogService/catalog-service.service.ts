import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {StoreCatalog} from "../../models/storeCatalog";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CatalogServiceService {
  readonly ADD_CATALOG = 'http://localhost:9090/kaddem/StoreCatalog/addStoreCatalog';
  readonly GET_ALL_CATALOG = 'http://localhost:9090/kaddem/StoreCatalog/get_all_StoreCatalog';
  readonly DELETE_CATALOG = 'http://localhost:9090/kaddem/StoreCatalog/deleteStoreCatalog/';
  readonly FIND_BY_ID = 'http://localhost:9090/kaddem/StoreCatalog/updateStoreCatalog/';

  constructor(private httpClient: HttpClient) { }

  addCatalog(catalog: StoreCatalog): Observable<any> {
    return this.httpClient.post(this.ADD_CATALOG, catalog);
  }
  getAllCatalog() {
    return this.httpClient.get<StoreCatalog[]>(this.GET_ALL_CATALOG);
  }
  deleteCatalog(id: number){
    return this.httpClient.delete(this.DELETE_CATALOG + id);
  }

  editCatalog(id){
    return this.httpClient.get<StoreCatalog>(this.FIND_BY_ID + id);
  }
}
