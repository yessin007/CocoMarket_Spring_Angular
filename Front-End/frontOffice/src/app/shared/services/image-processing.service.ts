import { Injectable } from '@angular/core';
import {DomSanitizer} from "@angular/platform-browser";
import {FileHandle, Product} from "../classes/product";
import {CartItem} from "../classes/CartItem";

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImages(product: Product) {
    const productImages: any[] = product.image;
    const productImagesToFileHandle: FileHandle[] = [];
    // tslint:disable-next-line:prefer-for-of
    for(let i = 0; i < productImages.length; i++){
      const imageFileData = productImages[i];
      const imageBlob = this.dataURItoBlob(imageFileData.picByte, imageFileData.type);
      const imageFile = new File([imageBlob], imageFileData.name, {type: imageFileData.type});
      const reader = new FileReader();
      reader.readAsDataURL(imageFile);
      const finalFileHandle: FileHandle = {
        filefile: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile)),
        preview: reader.result as string
      };
      productImagesToFileHandle.push(finalFileHandle);
    }
    product.image = productImagesToFileHandle;
    return product;
  }
  public createImagess(cartItem: CartItem) {
    const productImages: any[] = cartItem.product.image;
    const productImagesToFileHandle: FileHandle[] = [];
    // tslint:disable-next-line:prefer-for-of
    for(let i = 0; i < productImages.length; i++){
      const imageFileData = productImages[i];
      const imageBlob = this.dataURItoBlob(imageFileData.picByte, imageFileData.type);
      const imageFile = new File([imageBlob], imageFileData.name, {type: imageFileData.type});
      const reader = new FileReader();
      reader.readAsDataURL(imageFile);
      const finalFileHandle: FileHandle = {
        filefile: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile)),
        preview: reader.result as string
      };
      productImagesToFileHandle.push(finalFileHandle);
    }
    cartItem.product.image = productImagesToFileHandle;
    return cartItem.product;
  }
  public dataURItoBlob(picBytes, imageType){
    const byteString = window.atob(picBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++){
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: imageType });
    return blob;
  }
}
