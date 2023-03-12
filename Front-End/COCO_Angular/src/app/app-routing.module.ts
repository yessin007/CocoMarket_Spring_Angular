import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GoogleComponent} from "./login/google/google.component";

const routes: Routes = [
  { path: 'google', component: GoogleComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
