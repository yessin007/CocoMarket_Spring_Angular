import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {TableService} from '../../../shared/service/table.service';
import {ProviderService} from '../../../services/provider/provider.service';
import {Router} from '@angular/router';
import {LISTPAGEDB} from '../../../shared/tables/list-pages';
import {NgbdSortableHeader, SortEvent} from '../../../shared/directives/NgbdSortableHeader';
import {Observable} from 'rxjs';
import {Provider} from '../../../models/provider';
import {StoreCatalog} from '../../../models/storeCatalog';
import {CatalogServiceService} from '../../../services/catalogService/catalog-service.service';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {ImageProcessingService} from '../../../services/str-cat/image-processing.service';
import {map} from 'rxjs/operators';
import {Store} from '../../../models/store';
import {DatePipe} from '@angular/common';
@Component({
  selector: 'app-list-catl',
  templateUrl: './list-catl.component.html',
  styleUrls: ['./list-catl.component.scss']
})
export class ListCatlComponent implements OnInit{

  catalogList: StoreCatalog[] = [];
  store: Store = new Store();
  // tslint:disable-next-line:max-line-length
  constructor(private catalogservice: CatalogServiceService, private route: Router, private http: HttpClient , private  imageProcessingService: ImageProcessingService){}
  ngOnInit(): void {
    this.getAllCatalogs();

  }

  public getAllCatalogs(){
    this.catalogservice.getAllCatalog()
        .pipe(

            map((x: StoreCatalog[], i) => x.map((catalog: StoreCatalog) => this.imageProcessingService.createImages(catalog)))
        )
        .subscribe(
            (resp: StoreCatalog[]) => {console.log(resp); this.catalogList = resp;
                                       this.catalogList.forEach(catalog => {
                this.catalogservice.getCatalogStoreId(catalog.catalogId).subscribe(store => catalog.storeName = store.storeName);
              });
              },
            (error: HttpErrorResponse) => {console.log(error); }
        );
  }
  deleteCatalog(id){
    this.catalogservice.deleteCatalog(id).subscribe((response) => {
      this.getAllCatalogs();
    });
  }
  public editCatalogDetails(catalogId){
    // @ts-ignore
    this.route.navigate(['/vendors/create-storecatalog', {catalogId}]);
  }
  public getStoreByCatalogId(catalogId){
    this.catalogservice.getCatalogStoreId(catalogId).subscribe((resp) => {
      this.store = resp;
    });
    return this.store.storeName;
  }


}
