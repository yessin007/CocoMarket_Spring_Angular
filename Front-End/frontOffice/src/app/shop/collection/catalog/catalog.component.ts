import {Component, OnInit} from '@angular/core';
import {CatalogService} from '../../../services/catalog.service';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {StoreCatalog} from '../../../shared/models/StoreCatalog';
import {map} from 'rxjs/operators';
import {ImageProcessingService} from "../../../shared/services/image-processing.service";
import {ImageMalProcessingService} from "../../../shared/services/str-cat/imageMal-processing.service";

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.scss']
})
export class CatalogComponent implements OnInit{
  storeCatalog: StoreCatalog = new StoreCatalog();
  private catalogList: StoreCatalog[];

  constructor(private catalogservice: CatalogService, private route: Router, private http: HttpClient , private  imageProcessingService: ImageMalProcessingService){}
  ngOnInit(): void {
    this.getAllCatalogs();

  }

  public getAllCatalogs(){
    this.catalogservice.getAllCatalog()
        .pipe(

            map((x: StoreCatalog[], i) => x.map((catalog: StoreCatalog) => this.imageProcessingService.createImagesMal(catalog)))
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

}
