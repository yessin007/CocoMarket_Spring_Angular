import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListVendorsComponent } from './list-vendors/list-vendors.component';
import { CreateVendorsComponent } from './create-vendors/create-vendors.component';
import {CreateStorecatalogComponent} from './create-storecatalog/create-storecatalog.component';
import {ListCatlComponent} from "./list-catl/list-catl.component";


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

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class VendorsRoutingModule { }
