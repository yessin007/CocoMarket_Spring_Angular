import {Component, OnInit} from '@angular/core';
import {Store} from '../../../models/store';
import {ModalDismissReasons, NgbModal, NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {Image} from '@ks89/angular-modal-gallery';
import {ActivatedRoute, Router} from '@angular/router';
import {ImageProcessingService} from "../../../services/image-processing.service";
import {StoreService} from "../../../services/store/store.service";

// @ts-ignore
@Component({
  selector: 'app-store-detail',
  templateUrl: './store-detail.component.html',
  styleUrls: ['./store-detail.component.scss']

})
export class StoreDetailComponent implements OnInit{
  store: Store;
  image: File[] = [];
  public closeResult: string;
  public counter: number = 1;
  currentRate = 8;

  public imagesRect: Image[] = [
    new Image(0, { img: 'assets/imagesS/store1/2.jpg' }, { img: 'assets/imagesS/pro3/1.jpg' }),
    new Image(1, { img: 'assets/imagesS/store1/3.jpg' }, { img: 'assets/imagesS/pro3/27.jpg' }),
    new Image(2, { img: 'assets/imagesS/store1/1.jpg' }, { img: 'assets/imagesS/store1/1.jpg' }),
    new Image(3, { img: 'assets/imagesS/store1/2.jpg' }, { img: 'assets/imagesS/store1/2.jpg' })];


  constructor(private modalService: NgbModal, config: NgbRatingConfig, private activatedRoute: ActivatedRoute,
              private imageProcessingImage: ImageProcessingService, private route: Router , private storeService: StoreService) {
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
  ngOnInit(): void {
    this.store = this.activatedRoute.snapshot.data.store;
    console.log(this.store);
  }
  public AddProduct(storeID) {
    this.route.navigate(['/products/digital/digital-add-product',  {storeId: storeID}]);
  }
  affectProductToStore() {
    const storeId = 1; // l'identifiant du magasin
    const productId = 2; // l'identifiant du produit
    this.storeService.affectProductToStore(storeId, productId).subscribe(() => {
      console.log('Le produit a été affecté au magasin.');
    });
  }

  showAffect(storeId) {
    this.route.navigate(['/vendors/create-storecatalog', {storeId}]);
  }

}
