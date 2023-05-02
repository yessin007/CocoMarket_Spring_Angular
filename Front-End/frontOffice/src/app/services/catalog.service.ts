import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StoreCatalog} from "../shared/models/StoreCatalog";

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  readonly GET_ALL_CATALOG = 'http://localhost:8089/maram/StoreCatalog/get_all_StoreCatalog';
  readonly FIND_CATALOG_STORE = 'http://localhost:8089/maram/StoreCatalog/findStoreId/';

  constructor(private httpClient: HttpClient) { }

  getAllCatalog() {
    return this.httpClient.get<StoreCatalog[]>(this.GET_ALL_CATALOG);
  }

  getCatalogStoreId(catalogId){
    return this.httpClient.get<StoreCatalog>(this.FIND_CATALOG_STORE + catalogId);
  }
}
