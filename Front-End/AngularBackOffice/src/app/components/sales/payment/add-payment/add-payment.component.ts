import {Component, OnInit} from '@angular/core';
import {Order} from "../../../../models/order";
import {Payement} from "../../../../models/Payement";
import {UntypedFormBuilder} from "@angular/forms";
import {NgbCalendar} from "@ng-bootstrap/ng-bootstrap";
import {OrderService} from "../../../../services/order/order.service";
import {AuthService} from "../../../../services/auth.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrls: ['./add-payment.component.scss']
})
export class AddPaymentComponent implements OnInit{

  order: Order = new Order();
  payement:Payement = new Payement();

  constructor(private formBuilder: UntypedFormBuilder, private calendar: NgbCalendar,
              private orderService: OrderService, private auth: AuthService, private route: ActivatedRoute) {

  }
ngOnInit() {
}

  onSubmit() {

    this.orderService.addPayement( this.payement ).subscribe((payement: Payement) => {console.log('payement added successfully', payement);
          this.payement = new Payement(); } ,
        (error) => { console.error('Failed to add Order', error); }
    );
  }
}
