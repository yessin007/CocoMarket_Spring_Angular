import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListVendorsComponent } from './list-vendors/list-vendors.component';
import { CreateVendorsComponent } from './create-vendors/create-vendors.component';

import {CreateStorecatalogComponent} from './create-storecatalog/create-storecatalog.component';
import {ListCatlComponent} from "./list-catl/list-catl.component";

import {StoreDetailComponent} from "./store-detail/store-detail.component";
import {AllStoresComponent} from './all-stores/all-stores.component';
import {CatalogDetailComponent} from './catalog-detail/catalog-detail.component';



const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'list-vendors',
        component: ListVendorsComponent,
        data: {
          title: 'Vendor List',
          breadcrumb: 'Vendor List'
        }
      },
      {
        path: 'create-vendors',
        component: CreateVendorsComponent,
        data: {
          title: 'Create Store',
          breadcrumb: 'Create Store'
        }
      },{
       path: 'store-detail',
        component: StoreDetailComponent,
        data: {
          title: 'Store Detail',
          breadcrumb: 'Store Detail'
        }
      },
      {
        path: 'all-stores',
        component: AllStoresComponent,
        data: {
          title: 'All Stores',
          breadcrumb: ' '
        }
      },
      {

        path: 'create-storecatalog',
        component: CreateStorecatalogComponent,
        data: {
          title: 'Create Store Catalog',
          breadcrumb: 'Create StoreCatalog'
        }
      },
      {
        path: 'list-catl',
        component: ListCatlComponent,
        data: {
          title: 'list Catalog',
          breadcrumb: 'list StoreCatalog'
        }
      },
      {
        path: 'catalog-detail',
        component: CatalogDetailComponent,
        data: {
          title: 'catalog detail',
          breadcrumb: 'catalog detail'
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
