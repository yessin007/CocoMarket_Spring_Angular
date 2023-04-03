import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UnderConstructionComponent} from "./demo/under-construction/under-construction.component";

const routes: Routes = [
  { path: '**', component : UnderConstructionComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
