import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Image } from '@ks89/angular-modal-gallery';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import {Product} from '../../../../models/product';
import {ActivatedRoute, Router} from '@angular/router';
import {ImageProcessingService} from '../../../../services/image-processing.service';
import {Store} from '../../../../models/store';
import {StoreService} from '../../../../services/store/store.service';
import {ImageProceesingsService} from '../../../../services/img/image-proceesings.service';
import {ProductService} from '../../../../services/product/product.service';


@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss'],
  providers: [NgbRatingConfig]
})
export class ProductDetailComponent implements OnInit {
  public closeResult: string;
  public counter: number = 1;
  currentRate = 8;

  product: Product;
  image: File[] = [];

  public imagesRect: Image[] = [
    new Image(0, { img: 'assets/images/pro3/2.jpg' }, { img: 'assets/images/pro3/1.jpg' }),
    new Image(1, { img: 'assets/images/pro3/27.jpg' }, { img: 'assets/images/pro3/27.jpg' }),
    new Image(2, { img: 'assets/images/pro3/1.jpg' }, { img: 'assets/images/pro3/1.jpg' }),
    new Image(3, { img: 'assets/images/pro3/2.jpg' }, { img: 'assets/images/pro3/2.jpg' })];


  constructor(private modalService: NgbModal, config: NgbRatingConfig, private activatedRoute: ActivatedRoute,

              private imageProcessingImage: ImageProcessingService , private storeService: StoreService
  ,           private imageProcessingService: ImageProceesingsService , private productservice: ProductService,
              private route: ActivatedRoute ) {

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

  increment() {
    this.counter += 1;
  }

  decrement() {
    this.counter -= 1;
  }

  ngOnInit() {
    this.product = this.activatedRoute.snapshot.data.product;
    this.productservice.getAverageLikesOfProduct(this.product.productId).subscribe((resp) => this.product.avgLikes = resp);
    console.log(this.product);
  }

}
