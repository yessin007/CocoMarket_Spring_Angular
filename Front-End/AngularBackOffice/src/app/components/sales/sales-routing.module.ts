import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrdersComponent } from './orders/orders.component';
import { TransactionsComponent } from './transactions/transactions.component';
import {AddProductComponent} from "../products/physical/add-product/add-product.component";
import {AddOrderComponent} from "./orders/add-order/add-order.component";
import {PaymentListComponent} from "./payment/payment-list/payment-list.component";
import {AddPaymentComponent} from "./payment/add-payment/add-payment.component";
import {ListPaymComponent} from "./payment/list-paym/list-paym.component";

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'orders',
        component: OrdersComponent,
        data: {
          title: "Orders",
          breadcrumb: "Orders"
        }
      },
      {
        path: 'orders/add-order',
        component: AddOrderComponent,
        data: {
          title: "Add Orders",
          breadcrumb: "Add Orders"
        },
      },
      {
        path: 'payment/list-paym',
        component: ListPaymComponent,
        data: {
          title: "Payment List",
          breadcrumb: "Payment List"
        },
      },
      {
        path: 'payment/add-payment',
        component: AddPaymentComponent,
        data: {
          title: "Add Payment",
          breadcrumb: "Add Payment"
        },
      },
      {
        path: 'transactions',
        component: TransactionsComponent,
        data: {
          title: "Transactions",
          breadcrumb: "Transactions"
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalesRoutingModule { }
