import {Component, OnInit} from '@angular/core';
import {NgbModal, NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {ActivatedRoute} from '@angular/router';
import {ImageMalProcessingService} from '../../../services/str-cat/imageMal-processing.service';
import {CatalogServiceService} from '../../../services/catalogService/catalog-service.service';
import {StoreCatalog} from '../../../models/storeCatalog';
import {NgForm} from '@angular/forms';
import {Store} from '../../../models/store';

@Component({
  selector: 'app-affect-store',
  templateUrl: './affect-store.component.html',
  styleUrls: ['./affect-store.component.scss']
})
export class AffectStoreComponent implements OnInit{
  storeCatalog: StoreCatalog = new StoreCatalog();
  store: Store;
  catalogId: number;
  storeId: number;
  a: number;
  b: number;

  constructor(private modalService: NgbModal, config: NgbRatingConfig, private activateRoute: ActivatedRoute,
              // tslint:disable-next-line:max-line-length
              private imageProcessingImage: ImageMalProcessingService, private catalogService: CatalogServiceService, private inputElement: HTMLInputElement){}
  ngOnInit(): void {
    this.catalogId = +this.activateRoute.snapshot.paramMap.get('catalogId');
    this.catalogService.editCatalog(this.catalogId).subscribe(storeCatalog => {
      this.storeCatalog = storeCatalog;
    });
    console.log(this.catalogId);
    console.log(this.b);
  }

  onSubmit(catalogForm: NgForm){
    this.a = this.store.storeId;
    this.catalogService.addCatalogStore(this.catalogId, this.a);
  }



}
