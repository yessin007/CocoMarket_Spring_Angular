import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SalesRoutingModule } from './sales-routing.module';
import { OrdersComponent } from './orders/orders.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { SharedModule } from 'src/app/shared/shared.module';
import { AddOrderComponent } from './orders/add-order/add-order.component';


@NgModule({

  declarations: [OrdersComponent, TransactionsComponent, AddOrderComponent],
    imports: [
        CommonModule,
        SalesRoutingModule,
        NgbModule,
        FormsModule,
        Ng2SearchPipeModule,
        SharedModule,
        Ng2SearchPipeModule,
        ReactiveFormsModule
    ]
})
export class SalesModule { }
