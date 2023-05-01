import {Component, OnInit, ViewChildren} from '@angular/core';
import {NgForm, UntypedFormBuilder, UntypedFormGroup} from '@angular/forms';

import {CatalogServiceService} from '../../../services/catalogService/catalog-service.service';
import {StoreCatalog} from '../../../models/storeCatalog';
import {ActivatedRoute, Router} from '@angular/router';
import {FileHandle} from '../../../models/FileHandle';
import {DomSanitizer} from '@angular/platform-browser';
import {FileHandleMal} from '../../../models/FlileHandleMal';
import {Store} from "../../../models/store";


@Component({
  selector: 'app-create-storecatalog',
  templateUrl: './create-storecatalog.component.html',
  styleUrls: ['./create-storecatalog.component.scss']
})
export class CreateStorecatalogComponent implements OnInit{
    public generalForm: UntypedFormGroup;
    public seoForm: UntypedFormGroup;
    public active = 1;
    array: FileHandle[] = [];
    files: File[] = [] ;
    a: number;
    b: number;
    catalogList: StoreCatalog[] = [];
  catalog: StoreCatalog = new StoreCatalog();

  store: Store = new Store();

// tslint:disable-next-line:max-line-length
constructor(private formBuilder: UntypedFormBuilder, private sanitizer: DomSanitizer, private catalogservice: CatalogServiceService, private activatedRoute: ActivatedRoute, private route: Router) {
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
    createPermissionForm() {
    this.generalForm = this.formBuilder.group({});
    }

  onSubmit(catalogForm: NgForm) {
    this.catalog.catalogImages = this.array;
    console.log(this.catalog);
    const catalogFormData = this.prepareFormData(this.catalog);
    this.catalogservice.addCatalog(catalogFormData).subscribe(
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

    onAffect() {
        this.catalog.catalogImages = this.array;
        console.log(this.catalog);
        const catalogFormData = this.prepareFormData(this.catalog);
        this.catalogservice.addCatalog(catalogFormData).subscribe(
            (catalog: StoreCatalog) => {
                console.log('catalog added successfully', catalog);
                this.catalogservice.addCatalogStore(catalog.catalogId, catalog.storeId).subscribe(resp => console.log('suucc'));
                // Reset the form
                this.catalog = new StoreCatalog();
            },
            (error) => {
                console.error('Failed to add catalog', error);
            }
        );
    }

  prepareFormData(catalog: StoreCatalog): FormData{
      const formData = new FormData();

      formData.append('storeCatalog', new Blob([JSON.stringify(catalog)], {type: 'application/json'}));
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < catalog.catalogImages.length ; i++) {
          formData.append('imageFile', catalog.catalogImages[i].filemal, catalog.catalogImages[i].filemal.name);
      }
      return formData;
}

    onSelect(event) {
        console.log(event);
        // this.files.push(...event.addedFiles);
        if (event.addedFiles){
            const file = event.addedFiles[0];
            const fileHandleMal: FileHandleMal = {
                filemal: file,
                url: this.sanitizer.bypassSecurityTrustUrl(
                    window.URL.createObjectURL(file)
                )
            };
            // @ts-ignore
            this.array.push(fileHandleMal);
            this.catalog.catalogImages = this.array;
        }
    }

    onRemove(event) {
        console.log(event);
        this.array.splice(this.array.indexOf(event), 1);
    }
    ngOnInit(): void {
        this.catalog = this.activatedRoute.snapshot.data.catalog;
        console.log(this.catalog);
    }





}


