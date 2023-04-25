import { DecimalPipe } from '@angular/common';
import {Component, Inject, OnInit, QueryList, ViewChildren} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { SortEvent } from 'src/app/shared/directives/shorting.directive';
import { NgbdSortableHeader } from "src/app/shared/directives/NgbdSortableHeader";
import { TableService } from 'src/app/shared/service/table.service';
import { DigitalCategoryDB } from 'src/app/shared/tables/digital-category';
import { DIGITALLIST, DigitalListDB } from 'src/app/shared/tables/digital-list';
import {ProductService} from "../../../../services/product/product.service";
import {Product} from "../../../../models/product";
import {HttpErrorResponse} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {
  ShowProductImagesDialogComponent
} from "../../../show-product-images-dialog/show-product-images-dialog.component";
import {ImageProcessingService} from "../../../../services/image-processing.service";
import {map} from "rxjs/operators";
import {Router} from "@angular/router";
import {FileHandle} from "../../../../models/FileHandle";


@Component({
  selector: 'app-digital-list',
  templateUrl: './digital-list.component.html',
  styleUrls: ['./digital-list.component.scss'],
  providers: [TableService, DecimalPipe],
})
export class DigitalListComponent implements OnInit {
  tableItem$: Observable<DigitalListDB[]>;
  public digitalCategories: Product[] = [];
  array: FileHandle[] = [];

  constructor(public service: TableService, private modalService: NgbModal, private productService: ProductService, private imagediag: MatDialog,
              private  imageProcessingService: ImageProcessingService, private route: Router ) {
  }

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;
  image: FileHandle;

  onSort({ column, direction }: SortEvent) {
    console.log("ddsds");

    // resetting other headers
    this.headers.forEach((header) => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.service.sortColumn = column;
    this.service.sortDirection = direction;

  }
  public getAllProducts(){
    this.productService.getAllProducts()
        .pipe(
            map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
        )
        .subscribe(
        (resp: Product[]) => {console.log(resp); this.digitalCategories = resp; },
        (error: HttpErrorResponse) => {console.log(error); }
    );
  }
  public  deleteProduct(productId) {
    this.productService.deleteProduct(productId).subscribe(
        (resp) => {
          console.log(resp);
          this.getAllProducts();
        },
        (err: HttpErrorResponse) => {
      console.log(err);
    }
    );
  }
  public showImages(product: Product){
    console.log(product);
    this.imagediag.open(ShowProductImagesDialogComponent, {
      data: {
        images: product.productImages
      },
      height: '500px',
      width: '800px'
    });
  }
  public editProductDetails(productID) {
      this.route.navigate(['/products/digital/digital-add-product', {productId: productID}]);
  }
  showProductDetails(productID) {
    this.route.navigate(['/products/physical/product-detail', {productId: productID}]);
  }
  ngOnInit() {
    this.getAllProducts();
  }
}
