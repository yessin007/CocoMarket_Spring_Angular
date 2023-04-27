import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListVendorsComponent } from './list-vendors/list-vendors.component';
import { CreateVendorsComponent } from './create-vendors/create-vendors.component';
import {ProductDetailComponent} from "../products/physical/product-detail/product-detail.component";
import {StoreDetailComponent} from "./store-detail/store-detail.component";


const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'list-vendors',
        component: ListVendorsComponent,
        data: {
          title: "Vendor List",
          breadcrumb: "Vendor List"
        }
      },
      {
        path: 'create-vendors',
        component: CreateVendorsComponent,
        data: {
          title: "Create Store",
          breadcrumb: 'Create Store'
        }
      },
      {
        path: 'store-detail',
        component: StoreDetailComponent,
        data: {
          title: "Store Detail",
          breadcrumb: "Store Detail"
        }
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class VendorsRoutingModule { }
