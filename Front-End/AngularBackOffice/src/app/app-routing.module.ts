import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { content } from './shared/routes/content-routes';
import { ContentLayoutComponent } from './shared/layout/content-layout/content-layout.component';
import { LoginComponent } from './components/auth/login/login.component';
import {ProductDetailComponent} from './components/products/physical/product-detail/product-detail.component';
import {ProductResolverService} from './services/product-resolver/product-resolver.service';
import {ProductsModule} from './components/products/products.module';
import {DigitalAddComponent} from './components/products/digital/digital-add/digital-add.component';
import {CreateStorecatalogComponent} from './components/vendors/create-storecatalog/create-storecatalog.component';
import {StrCtlgResolverService} from './services/store-catalog/str-ctlg-resolver.service';

import {CreateVendorsComponent} from './components/vendors/create-vendors/create-vendors.component';
import {StoreResolverService} from './services/store-resolver/store-resolver.service';
import {StoreDetailComponent} from './components/vendors/store-detail/store-detail.component';

import {AddOrderComponent} from './components/sales/orders/add-order/add-order.component';
import {resolve} from '@angular/compiler-cli';
import {OrderResolverServiceService} from './services/order-resolver/order-resolver-service.service';
import {MapComponent} from "./components/map/map.component";



const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard/default',
    pathMatch: 'full'
  },
  {
   path: '',
    component: ContentLayoutComponent,
      children: [
            {path: 'products/physical/product-detail' , component: ProductDetailComponent,  resolve : {
                    product: ProductResolverService
              }}
      ],
  },
  {
    path: '',
    component: MapComponent,
    children: [
      {path: 'map' , component: MapComponent}
    ],
  },

  {
    path: '',
    component: ContentLayoutComponent,
    children: [
      {path: 'products/digital/digital-add-product' , component: DigitalAddComponent, resolve : {
          product: ProductResolverService
        }  }
    ],
  }, {
    path: '',
    component: ContentLayoutComponent,
    children: [
      {path: 'sales/orders/add-order' , component: AddOrderComponent, resolve : {
          order: OrderResolverServiceService
        }  }
    ],
  },
  {
    path: '',
    component: ContentLayoutComponent,
    children: [
      {path: 'vendors/store-detail' , component: StoreDetailComponent,  resolve : {
          store: StoreResolverService
        }}
    ],
  },
  {
    path: '',
    component: ContentLayoutComponent,
    children: [
      {path: 'vendors/create-vendors' , component: CreateVendorsComponent, resolve : {
          store: StoreResolverService
        }  }
    ],
  },
  {
    path: '',
    component: ContentLayoutComponent,
    children: [
      {path: 'vendors/create-storecatalog' , component: CreateStorecatalogComponent, resolve : {
          catalog: StrCtlgResolverService
        }  }
    ],
  },
  {
    path: '',
    component: ContentLayoutComponent,
    children: content
  },
  {
    path: 'auth/login',
    component: LoginComponent,
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    scrollPositionRestoration: 'enabled'
})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
