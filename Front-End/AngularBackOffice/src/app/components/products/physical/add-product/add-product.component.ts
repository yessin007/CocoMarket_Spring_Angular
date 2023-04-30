import { Component, OnInit } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import {FileHandle} from "../../../../models/FileHandle";
import {ProductService} from "../../../../services/product/product.service";
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../../../models/product";


@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent implements OnInit {
  public productForm: UntypedFormGroup;
  public Editor = ClassicEditor;
  public counter = 1;
  files: File[] = [];
  array: FileHandle[] = [];
 public product: Product = new Product() ;


  constructor(private fb: UntypedFormBuilder,private productService: ProductService, private sanitizer: DomSanitizer, private activatedRoute: ActivatedRoute) {
    this.productForm = this.fb.group({
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z][a-zA-Z ]+[a-zA-Z]$')]],
      price: ['', [Validators.required, Validators.pattern('[a-zA-Z][a-zA-Z ]+[a-zA-Z]$')]],
      code: ['', [Validators.required, Validators.pattern('[a-zA-Z][a-zA-Z ]+[a-zA-Z]$')]],
      size: ['', Validators.required],
    })
  }

  increment() {
    this.counter += 1;
  }

  decrement() {
    this.counter -= 1;
  }

  //FileUpload
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
  ngOnInit() {
  }

}
