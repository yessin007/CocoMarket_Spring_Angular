import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ShopComponent } from './shop/shop.component';
import { PagesComponent } from './pages/pages.component';
import { ElementsComponent } from './elements/elements.component';
import {ShopCollectionComponent} from "./shop/shop-collection/shop-collection.component";
import {PostStoreComponent} from "./shop/collection/post-store/post-store.component";
import {CollectionComponent} from "./pages/collection/collection.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home/fashion',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadChildren: () => import('./home/home.module').then(m => m.HomeModule)
  },

  {
    path: '',
    component: ShopComponent,
    children: [
      {path: 'shop/collection/shop-collection', component: ShopCollectionComponent}
    ]
  },
  {
    path: '',
    component: ShopComponent,
    children: [
      {path: 'pages/collection', component: CollectionComponent}
    ]
  },

  {
    path: '',
    component: PostStoreComponent,
    children: [
      {path: 'shop/collection/post-store', component: PostStoreComponent}
    ]
  },
  {
    path: 'shop',
    component: ShopComponent,
    loadChildren: () => import('./shop/shop.module').then(m => m.ShopModule)
  },
  { 
    path: 'pages',
    component: PagesComponent,
    loadChildren: () => import('./pages/pages.module').then(m => m.PagesModule) 
  },
  { 
    path: 'elements', 
    component: ElementsComponent,
    loadChildren: () => import('./elements/elements.module').then(m => m.ElementsModule) },
  {
    path: '**', // Navigate to Home Page if not found any page
    redirectTo: 'home/fashion',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    useHash: false,
    anchorScrolling: 'enabled',
    scrollPositionRestoration: 'enabled'
})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
