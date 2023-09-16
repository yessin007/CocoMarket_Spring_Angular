import { Component } from '@angular/core';
import {UntypedFormBuilder, UntypedFormGroup} from '@angular/forms';
import {NgbCalendar, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import {Order} from 'src/app/models/order';
import {Store} from '../../../../models/store';

import {OrderService} from '../../../../services/order/order.service';
import {AuthService} from '../../../../services/auth.service';
import {User} from '../../../../models/User';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.scss']
})
export class AddOrderComponent {

  public generalForm: UntypedFormGroup;
  public restrictionForm: UntypedFormGroup;
  public usageForm: UntypedFormGroup;
  public model: NgbDateStruct;
  public date: { year: number, month: number };
  public modelFooter: NgbDateStruct;
  public active = 1;
  order: Order = new Order();
  store: Store = new Store();
  // tslint:disable-next-line:new-parens
  currentUser: User = new User;
  // tslint:disable-next-line:ban-types
  protected currentToken!: String;

  constructor(private formBuilder: UntypedFormBuilder, private calendar: NgbCalendar,
              private orderService: OrderService, private auth: AuthService, private route: ActivatedRoute, private  router:Router) {
    this.createGeneralForm();
    this.createRestrictionForm();
    this.createUsageForm();
    this.auth.currentUser.subscribe(data => {
      this.currentUser = data;
    });
    this.auth.currentToken.subscribe( data => {
      this.currentToken = data;
    });
  }

  selectToday() {
    this.model = this.calendar.getToday();
  }

  createGeneralForm() {
    this.generalForm = this.formBuilder.group({
      name: [''],
      code: [''],
      start_date: [''],
      end_date: [''],
      free_shipping: [''],
      quantity: [''],
      discount_type: [''],
      status: [''],
    });
  }

  createRestrictionForm() {
    this.restrictionForm = this.formBuilder.group({
      products: [''],
      category: [''],
      min: [''],
      max: ['']
    });
  }

  createUsageForm() {
    this.usageForm = this.formBuilder.group({
      limit: [''],
      customer: ['']
    });
  }
  // tslint:disable-next-line:use-lifecycle-interface
  ngOnInit() {
    this.order = this.route.snapshot.data.order;
    console.log(this.order);  }
  onSubmit() {
    console.log(this.currentToken);
    this.orderService.addOrder( this.order ).subscribe((order: Order) => {console.log('Order added successfully', order);
                                                                          this.order = new Order(); } ,
        (error) => { console.error('Failed to add Order', error); }
        );
    this.router.navigate(['/sales/orders']);
  }
}
