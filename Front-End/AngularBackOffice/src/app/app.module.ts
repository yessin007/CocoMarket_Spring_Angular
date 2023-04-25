import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { AgGridModule } from '@ag-grid-community/angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DashboardModule } from './components/dashboard/dashboard.module';
import { SharedModule } from './shared/shared.module';
import { ProductsModule } from './components/products/products.module';
import { SalesModule } from './components/sales/sales.module';
import { CouponsModule } from './components/coupons/coupons.module';
import { PagesModule } from './components/pages/pages.module';
import { MediaModule } from './components/media/media.module';
import { MenusModule } from './components/menus/menus.module';
import { VendorsModule } from './components/vendors/vendors.module';
import { UsersModule } from './components/users/users.module';
import { LocalizationModule } from './components/localization/localization.module';
import { InvoiceModule } from './components/invoice/invoice.module';
import { SettingModule } from './components/setting/setting.module';
import { ReportsModule } from './components/reports/reports.module';
import { AuthModule } from './components/auth/auth.module';
import {ProductService} from './services/product/product.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {MatDialogModule} from '@angular/material/dialog';
import { ShowProductImagesDialogComponent } from './components/show-product-images-dialog/show-product-images-dialog.component';

import {MatGridListModule} from "@angular/material/grid-list";
import { CarouselModule } from 'ngx-bootstrap/carousel';



@NgModule({
  declarations: [
    AppComponent,
    ShowProductImagesDialogComponent,
  ],
    imports: [
        BrowserAnimationsModule,
        BrowserModule.withServerTransition({appId: 'serverApp'}),
        AppRoutingModule,
        DashboardModule,
        InvoiceModule,
        SettingModule,
        ReportsModule,
        AuthModule,
        SharedModule,
        LocalizationModule,
        ProductsModule,
        SalesModule,
        VendorsModule,
        CouponsModule,
        PagesModule,
        MediaModule,
        MenusModule,
        UsersModule,
        AgGridModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        MatDialogModule,
        MatGridListModule,
        CarouselModule.forRoot(),
    ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
