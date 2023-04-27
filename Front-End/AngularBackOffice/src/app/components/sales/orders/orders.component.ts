import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { SortEvent } from 'src/app/shared/directives/shorting.directive';
import { NgbdSortableHeader } from "src/app/shared/directives/NgbdSortableHeader";
import { TableService } from 'src/app/shared/service/table.service';
import { Observable } from 'rxjs';
import { DecimalPipe } from '@angular/common';
import { OrderDB, ORDERDB } from 'src/app/shared/tables/order-list';
import {OrderService} from "../../../services/order/order.service";
import {Order} from "../../../models/order";
import {map} from "rxjs/operators";
import {Product} from "../../../models/product";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss'],
  providers: [TableService, DecimalPipe],
})

export class OrdersComponent implements OnInit {
  public selected = [];
  public closeResult: string;
  public tableItem$: Observable<OrderDB[]>;
  public searchText;
  total$: Observable<number>;
  public OrderList: Order[] = [];
  public digitalCategories: Product[] = [];

  constructor(public service: TableService, private modalService: NgbModal, private orderService: OrderService, private route: Router) {
    this.tableItem$ = service.tableItem$;
    this.total$ = service.total$;
    this.service.setUserData(ORDERDB)
  }

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach((header) => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.service.sortColumn = column;
    this.service.sortDirection = direction;

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

  onSelect({ selected }) {
    this.selected.splice(0, this.selected.length);
    this.selected.push(...selected);
  }
  editOrder(orderId){
    this.route.navigate(['/sales/orders/add-order',  {orderId}]);
  }

  ngOnInit() {
    this.getAllOrders();
  }
  getAllOrders(){
    this.orderService.getAllOrders().subscribe((response ) => {
      this.OrderList = response;
    });
  }





  deleteOrder(id){
    this.orderService.deleteOrder(id).subscribe((response) => {
      this.getAllOrders();
    });
  }



  // public  deleteProduct(productId) {
  //   this.productService.deleteProduct(productId).subscribe(
  //       (resp) => {
  //         console.log(resp);
  //         this.getAllProducts();
  //       },
  //       (err: HttpErrorResponse) => {
  //         console.log(err);
  //       }
  //   );
  // }



}
