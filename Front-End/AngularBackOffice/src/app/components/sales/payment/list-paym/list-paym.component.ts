import {AfterViewInit, Component, HostBinding, OnInit} from '@angular/core';
import {OrderService} from "../../../../services/order/order.service";
import {Payement} from "../../../../models/Payement";
import {PaymentService} from "../../../../services/payment/payment.service";
import {TableService} from "../../../../shared/service/table.service";
import {AuthService} from "../../../../services/auth.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";
import {ORDERDB} from "../../../../shared/tables/order-list";
import {User} from "../../../../models/User";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {animate, style, trigger} from "@angular/animations";

@Component({
  selector: 'app-list-paym',
  templateUrl: './list-paym.component.html',
  styleUrls: ['./list-paym.component.scss'],

})
export class ListPaymComponent implements  OnInit   {
  @HostBinding('@animateRoute') animateRoute = '';
  public PaymentList: Payement[] = [];
  protected currentToken!: String;
  currentUser: User = new User;

  constructor(public paymentService: PaymentService,  private orderService: OrderService, private route: Router) {
    //this.tableItem$ = service.tableItem$;
    //this.total$ = service.total$;
    //this.service.setUserData(ORDERDB);
    // this.auth.currentUser.subscribe(data => {
    //   this.currentUser = data;
    // });
    // this.auth.currentToken.subscribe( data => {
    //   this.currentToken = data;
    // });
  }
  // ngAfterViewInit() {
  //   this.animateRoute = '/sales/payment/list-paym';
  //   this.getAllPayments();
  // }
  ngOnInit() {
    this.getAllPayments();
    console.log();
  }

  // ngAfterViewInit(){
  //   this.getAllPayments();
  //   console.log();
  // }
  getAllPayments(){
    this.paymentService.getAllPayments().subscribe((response ) => {
      this.PaymentList = response;

    });
  }

  deletePayment(id){
    this.paymentService.deletePayment(id).subscribe((response) => {
           this.getAllPayments();
        });
  }


  // deleteOrder(id){
  //
  //   this.orderService.deleteOrder(id).subscribe((response) => {
  //     this.getAllOrders();
  //   });
  // }


  // editOrder(orderId){
  //   this.route.navigate(['/sales/orders/add-order',  {orderId}]);
  // }

}
