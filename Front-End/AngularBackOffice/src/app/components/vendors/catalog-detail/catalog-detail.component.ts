import {Component, OnInit} from '@angular/core';
import {StoreCatalog} from '../../../models/storeCatalog';
import {ActivatedRoute} from '@angular/router';
import {Store} from '../../../models/store';
import {Image} from '@ks89/angular-modal-gallery';
import {ModalDismissReasons, NgbModal, NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';

import {CatalogServiceService} from '../../../services/catalogService/catalog-service.service';
import {ImageMalProcessingService} from '../../../services/str-cat/imageMal-processing.service';

// @ts-ignore
// @ts-ignore
@Component({
  selector: 'app-catalog-detail',
  templateUrl: './catalog-detail.component.html',
  styleUrls: ['./catalog-detail.component.scss']
})
export class CatalogDetailComponent implements OnInit{
  // @ts-ignore
  storeCatalog: StoreCatalog = new StoreCatalog();

  catalogId: number ;
  image: File[] = [];
  public closeResult: string;
  constructor(private modalService: NgbModal, config: NgbRatingConfig, private activateRoute: ActivatedRoute,
              private imageProcessingImage: ImageMalProcessingService, private catalogService: CatalogServiceService) {

    config.max = 5;
    config.readonly = false;
  }
  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }


  ngOnInit(): void {
    // tslint:disable-next-line:radix
    this.storeCatalog = this.activateRoute.snapshot.data.store;
    this.getCatalog();

  }
  public getCatalog(){
    this.catalogService.getCatalogStoreId(this.storeCatalog.catalogId).subscribe(store => this.storeCatalog.storeName = store.storeName );
        }
}
