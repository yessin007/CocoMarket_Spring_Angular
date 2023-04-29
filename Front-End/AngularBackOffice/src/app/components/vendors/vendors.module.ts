import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VendorsRoutingModule } from './vendors-routing.module';
import { ListVendorsComponent } from './list-vendors/list-vendors.component';
import { CreateVendorsComponent } from './create-vendors/create-vendors.component';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {NgxDropzoneModule} from "ngx-dropzone";
import {SharedModule} from "../../shared/shared.module";
import { StoreDetailComponent } from './store-detail/store-detail.component';
import {CarouselModule} from "ngx-bootstrap/carousel";
import { AllStoresComponent } from './all-stores/all-stores.component';
import { CreateStorecatalogComponent } from './create-storecatalog/create-storecatalog.component';
import { ListCatlComponent } from './list-catl/list-catl.component';
import { AddPostComponent } from './add-post/add-post.component';
// import { Ng2SmartTableModule } from 'ng2-smart-table';

@NgModule({
  declarations: [ListVendorsComponent, CreateVendorsComponent, StoreDetailComponent, AllStoresComponent, CreateStorecatalogComponent, ListCatlComponent, AddPostComponent],

    imports: [
        CommonModule,
        VendorsRoutingModule,
        ReactiveFormsModule,
        NgbModule,
        FormsModule,
        NgxDropzoneModule,
        SharedModule,
        CarouselModule,
        // Ng2SmartTableModule
    ]
})
export class VendorsModule { }
