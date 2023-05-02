import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {NgbdSortableHeader, SortEvent} from "../../../../shared/directives/NgbdSortableHeader";
import {Observable} from "rxjs";
import {TRANSACTIONDB, TransactionsDB} from "../../../../shared/tables/transactions";
import {TableService} from "../../../../shared/service/table.service";
import {AuthService} from "../../../../services/auth.service";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {OrderService} from "../../../../services/order/order.service";
import {Router} from "@angular/router";
import {OrderDB, ORDERDB} from "../../../../shared/tables/order-list";
import {Order} from "../../../../models/order";
import {Product} from "../../../../models/product";
import {User} from "../../../../models/User";
import {Payement} from "../../../../models/Payement";

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.scss']
})
export class PaymentListComponent implements OnInit{

  public selected = [];
  public closeResult: string;
  public tableItem$: Observable<OrderDB[]>;
  public searchText;
  total$: Observable<number>;
  public OrderList: Order[] = [];
  public PaymentList: Payement[] = [];
  public digitalCategories: Product[] = [];
  // tslint:disable-next-line:ban-types
  protected currentToken!: String;



  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  // constructor(public service: TableService, private auth: AuthService, private modalService: NgbModal, private orderService: OrderService, private route: Router) {
  //   this.tableItem$ = service.tableItem$;
  //   this.total$ = service.total$;
  //   this.service.setUserData(ORDERDB);
  //   this.auth.currentUser.subscribe(data => {
  //     this.currentUser = data;
  //   });
  //   this.auth.currentToken.subscribe( data => {
  //     this.currentToken = data;
  //   });
  // }
  constructor(public orderService: OrderService) {
  }

  ngOnInit() {
  }


  // open(content) {
  //   this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
  //     this.closeResult = `Closed with: ${result}`;
  //   }, (reason) => {
  //     this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  //   });
  // }

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
    //this.route.navigate(['/sales/orders/add-order',  {orderId}]);
  }

  // tslint:disable-next-line:use-lifecycle-interface



  // getAllPayments(){
  //   this.orderService.getAllPayments().subscribe((response ) => {
  //     this.PaymentList = response;
  //   }
  // );
  // }






}

