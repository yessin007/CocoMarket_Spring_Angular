import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { content } from './shared/routes/content-routes';
import { ContentLayoutComponent } from './shared/layout/content-layout/content-layout.component';
import { LoginComponent } from './components/auth/login/login.component';
import {ProductDetailComponent} from "./components/products/physical/product-detail/product-detail.component";
import {ProductResolverService} from "./services/product-resolver/product-resolver.service";
import {ProductsModule} from "./components/products/products.module";
import {DigitalAddComponent} from "./components/products/digital/digital-add/digital-add.component";


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
    component: ContentLayoutComponent,
    children: [
      {path: 'products/digital/digital-add-product' , component: DigitalAddComponent, resolve : {
          product: ProductResolverService
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
