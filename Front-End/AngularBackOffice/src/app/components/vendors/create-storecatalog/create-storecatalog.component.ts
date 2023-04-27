import {Component, OnInit} from '@angular/core';
import {UntypedFormBuilder, UntypedFormGroup} from '@angular/forms';

import {CatalogServiceService} from '../../../services/catalogService/catalog-service.service';
import {StoreCatalog} from '../../../models/storeCatalog';


@Component({
  selector: 'app-create-storecatalog',
  templateUrl: './create-storecatalog.component.html',
  styleUrls: ['./create-storecatalog.component.scss']
})
export class CreateStorecatalogComponent implements OnInit{
    public generalForm: UntypedFormGroup;
    public seoForm: UntypedFormGroup;
    public active = 1;
  catalog: StoreCatalog = new StoreCatalog();

  constructor(private formBuilder: UntypedFormBuilder, private catalogservice: CatalogServiceService) {
      this.createGeneralForm();
      this.createSeoForm();
  }
    createGeneralForm() {
        this.generalForm = this.formBuilder.group({
            name: [''],
            desc: [''],
            status: ['']
        });
    }
    createSeoForm() {
        this.seoForm = this.formBuilder.group({
            title: [''],
            keyword: [''],
            meta_desc: ['']
        });
    }
  ngOnInit(): void {}
  onSubmit() {
    this.catalogservice.addCatalog(this.catalog).subscribe(
        (catalog: StoreCatalog) => {
          console.log('catalog added successfully', catalog);
          // Reset the form
          this.catalog = new StoreCatalog();
        },
        (error) => {
          console.error('Failed to add catalog', error);
        }
    );
  }
}


