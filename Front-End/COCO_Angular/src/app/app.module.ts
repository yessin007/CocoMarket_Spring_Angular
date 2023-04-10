import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UnderConstructionComponent } from './demo/under-construction/under-construction.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { ForgetpasswordComponent } from './auth/forgetpassword/forgetpassword.component';
import { ResetpasswordComponent } from './auth/resetpassword/resetpassword.component';
import { ActivateComponent } from './auth/activate/activate.component';
import { MenuComponent } from './backOffice/menu/menu.component';
import { AddProductComponent } from './backOffice/product/add-product/add-product.component';
import { AdminHomeComponent } from './backOffice/admin-home/admin-home.component';
import { ShowAllProductsComponent } from './backOffice/product/show-all-products/show-all-products.component';

@NgModule({
  declarations: [
    AppComponent,
    UnderConstructionComponent,
    LoginComponent,
    SignupComponent,
    ForgetpasswordComponent,
    ResetpasswordComponent,
    ActivateComponent,
    MenuComponent,
    AddProductComponent,
    AdminHomeComponent,
    ShowAllProductsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
