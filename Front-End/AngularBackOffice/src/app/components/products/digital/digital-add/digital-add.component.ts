import {Component, OnInit, Sanitizer} from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {Product} from '../../../../models/product';
import {ProductService} from '../../../../services/product/product.service';
import {NgForm} from '@angular/forms';
import {DomSanitizer} from '@angular/platform-browser';
import {FileHandle} from '../../../../models/FileHandle';
import {ActivatedRoute} from "@angular/router";
import {StoreService} from "../../../../services/store/store.service";
import {Store} from "../../../../models/store";

@Component({
  selector: 'app-digital-add',
  templateUrl: './digital-add.component.html',
  styleUrls: ['./digital-add.component.scss']
})


export class DigitalAddComponent implements OnInit {
  public counter = 1;
  files: File[] = [];
  selectedItems: string[] = ['all products'];
  array: FileHandle[] = [];
  public Editor = ClassicEditor;
  public  a: number ;
  store: Store = new Store();

  isNew: boolean;
  isTrending: boolean;
  constructor(private storeservice: StoreService, private productService: ProductService, private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute) { }
  product: Product = new Product();
  onCheckboxChange(item: string, isChecked: boolean) {
    if (isChecked) {
      this.selectedItems.push(item);
    } else {
      const index = this.selectedItems.indexOf(item);
      if (index !== -1) {
        this.selectedItems.splice(index, 1);
      }
    }
    console.log(this.selectedItems);
  }
  onSelect(event) {
    console.log(event);
    //this.files.push(...event.addedFiles);
    if (event.addedFiles){
      const file = event.addedFiles[0];
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
  onRemove(event) {
    console.log(event);
    this.array.splice(this.array.indexOf(event), 1);
  }
  increment() {
    this.counter += 1 ;
    this.product.yearsOfWarranty = this.counter;
  }
  decrement() {
    this.counter -= 1;
    this.product.yearsOfWarranty = this.counter;
  }
  clearDescription() {
    this.product.description = '';
  }
  onSubmit(productForm: NgForm) {
    this.product.productCategory = 'electronics';
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
  addProductToStore(): void {
    this.product.productCategory = 'electronics';
    this.product.collection = this.selectedItems;
    const productFormData = this.prepareFormData(this.product);
    this.productService.addProduct(productFormData).subscribe(
        (product: Product) => {
          console.log('Product added successfully', product);
          this.a = product.productId ;
          this.storeservice.addProductStore(this.store.storeId, product.productId).subscribe(resp => console.log('affected succ '));
          // Reset the form
          this.product = new Product();
        },
        (error) => {
          console.error('Failed to add product', error);
        }
    );

    // this.storeservice.addProductStore()



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
  ngOnInit() { this.product = this.activatedRoute.snapshot.data.product; console.log(this.product);
               this.store = this.activatedRoute.snapshot.data.store ;
               console.log(this.store); }

}
