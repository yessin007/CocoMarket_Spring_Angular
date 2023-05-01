import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PagesRoutingModule } from './pages-routing.module';
import { ListPageComponent } from './list-page/list-page.component';
import { CreatePageComponent } from './create-page/create-page.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProviderLocationComponent } from './provider-location/provider-location.component';

@NgModule({

  declarations: [ListPageComponent, CreatePageComponent, ProviderLocationComponent],

    imports: [
        CommonModule,
        PagesRoutingModule,
        NgbModule,
        ReactiveFormsModule,
        SharedModule,
        FormsModule
    ]

})
export class PagesModule { }
