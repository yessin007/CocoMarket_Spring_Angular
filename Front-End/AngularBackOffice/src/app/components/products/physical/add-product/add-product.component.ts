import { Component, OnInit } from '@angular/core';
import {
    UntypedFormGroup,
    UntypedFormBuilder,
    Validators,
    NgForm,
    FormControl,
    FormGroup,
    FormBuilder
} from '@angular/forms';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {FileHandle} from "../../../../models/FileHandle";
import {ProductService} from "../../../../services/product/product.service";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../../../models/product";
import {Store} from "../../../../models/store";
import {StoreService} from "../../../../services/store/store.service";


@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {



  constructor(private fb: UntypedFormBuilder, private productService: ProductService, private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute,
              private storeService: StoreService) {
    this.productForm = this.fb.group({
      name: ['', [Validators.required]],
      desc: ['', [Validators.required]],
      price: ['', [Validators.required]],
      code: ['', [Validators.required]],
      stock: ['', Validators.required],
      discount: [''],
    });
  }
  public productForm: UntypedFormGroup;
  public Editor = ClassicEditor;
  public counter = 1;
  files: File[] = [];
  array: FileHandle[] = [];
  store: Store = new Store();
    public  a: number ;
    selectedItems: string[] = ['all products'];
 public product: Product = new Product() ;
  //FileUpload
    onSale: boolean;
    isNew: boolean;
    isTrending: boolean;
    onCheckboxChange(item: string, isChecked: boolean) {
        if (isChecked) {
            this.selectedItems.push(item);
        } else {
            const index = this.selectedItems.indexOf(item);
            if (index !== -1) {
                this.selectedItems.splice(index, 1);
            }
        }
        this.product.collection = this.selectedItems;
        console.log(this.product.collection);
    }
  increment() {
    this.counter += 1;
    this.product.stock = this.counter;
  }

  decrement() {
    this.counter -= 1;
    this.product.stock = this.counter;
  }
    clearDescription() {
        this.product.description = '';
    }
    onSubmit() {
        this.product.productCategory = 'fashion';
        this.product.collection = this.selectedItems;
        const productFormData = this.prepareFormData(this.product);
        this.productService.addProduct(productFormData).subscribe(
            (product: Product) => {
                console.log('Product added successfully', product);
                // Reset the form
                this.product = new Product();
            },
            (error) => {
                console.error('Failed to add product', error);
            }
        );
        this.array = [];
    }

    prepareFormData(product: Product): FormData{
        const formData = new FormData();
        //this.product.productImages = this.files;
        formData.append('product', new Blob([JSON.stringify(product)], {type: 'application/json'}));
        // tslint:disable-next-line:prefer-for-of
        for (let i = 0; i < product.image.length ; i++) {
            formData.append('imageFile', product.image[i].filefile, product.image[i].filefile.name);
        }
        return formData;
    }
  readUrl(event: any, i) {
    if (event.target.files.length === 0) {
      return;
    }

    // Get the file from the input field
    const file: File = event.target.files[0];

    // Validate the file type
    const mimeType = file.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }

    // Create a URL for the file
    const fileUrl: SafeUrl = this.sanitizer.bypassSecurityTrustUrl(
        window.URL.createObjectURL(file)
    );

    // Create a file handle object
    const fileHandle: FileHandle = {
      filefile: file,
      url: fileUrl
    };

    // Update the array with the new file handle
    this.array[i] = fileHandle;
  }
  onSelect(event) {
    console.log(event);
    //this.files.push(...event.addedFiles);
    if (event.addedFiles){
      const file = event.addedFiles[0];
      console.log(file);
      const reader = new FileReader();
      reader.readAsDataURL(file);
      const url = this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)).toString();
      const fileHandle: FileHandle = {
        filefile: file,
        url: this.sanitizer.bypassSecurityTrustUrl(
            window.URL.createObjectURL(file)
        ),
        preview: this.sanitizer.bypassSecurityTrustUrl(
            window.URL.createObjectURL(file)
        ).toString(),
      };
      console.log(fileHandle);
      this.array.push(fileHandle);
      this.product.image = this.array;
      console.log(this.product.image);
    }
  }
    getSanitizedUrl(url: string): SafeUrl {
        return this.sanitizer.bypassSecurityTrustUrl(url);
    }
  onRemove(event) {
    console.log(event);
    this.array.splice(this.array.indexOf(event), 1);
  }
    addProductToStore(): void {
        this.product.productCategory = 'fashion';
        this.product.collection = this.selectedItems;
        const productFormData = this.prepareFormData(this.product);
        this.productService.addProduct(productFormData).subscribe(
            (product: Product) => {
                console.log('Product added successfully', product);
                this.a = product.productId ;
                this.storeService.addProductStore(this.store.storeId, product.productId).subscribe(resp => console.log('affected succ '));
                // Reset the form
                this.product = new Product();
            },
            (error) => {
                console.error('Failed to add product', error);
            }
        );
    }
  ngOnInit() {
      this.product = this.activatedRoute.snapshot.data.product; console.log(this.product);
      this.store = this.activatedRoute.snapshot.data.store ;
  }

}
