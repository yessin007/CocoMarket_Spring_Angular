import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UnderConstructionComponent} from "./demo/under-construction/under-construction.component";
import {LoginComponent} from "./auth/login/login.component";
import {ForgetpasswordComponent} from "./auth/forgetpassword/forgetpassword.component";
import {ActivateComponent} from "./auth/activate/activate.component";
import {ResetpasswordComponent} from "./auth/resetpassword/resetpassword.component";
import {SignupComponent} from "./auth/signup/signup.component";
import {MenuComponent} from "./backOffice/menu/menu.component";
import {AdminHomeComponent} from "./backOffice/admin-home/admin-home.component";
import {ShowAllProductsComponent} from "./backOffice/product/show-all-products/show-all-products.component";
import {HomeComponent} from "./frontOffice/home/home.component";

const routes: Routes = [
  { path: '', component: UnderConstructionComponent },
  { path: 'home', component: HomeComponent },
  { path: 'auth', children: [
      { path: 'login', component: LoginComponent },
      { path: 'forgetPassword', component: ForgetpasswordComponent },
      { path: 'Activate', component: ActivateComponent },
      { path: 'resetPassword', component: ResetpasswordComponent },
      { path: 'SignUp', component: SignupComponent },
    ]},
  {path:"adminpanel" ,component: MenuComponent, children: [
      {path: "home",component: AdminHomeComponent},
      {path: "products", component: ShowAllProductsComponent}
    ]},
  { path: '**', component: UnderConstructionComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

