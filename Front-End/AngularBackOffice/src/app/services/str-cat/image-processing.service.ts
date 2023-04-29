import { Injectable } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {FileHandle} from '../../models/FileHandle';
import {StoreCatalog} from '../../models/storeCatalog';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImages(catalog: StoreCatalog) {
    const catalogImages: any[] = catalog.catalogImages;
    const storeImagesToFileHandle: FileHandle[] = [];
    for(let i = 0; i < catalogImages.length; i++){
      const imageFileData = catalogImages[i];
      const imageBlob = this.dataURItoBlob(imageFileData.picByte, imageFileData.type);
      const imageFile = new File([imageBlob], imageFileData.name, {type: imageFileData.type});
      const reader = new FileReader();
      reader.readAsDataURL(imageFile);
      const finalFileHandle: FileHandle = {
        filefile: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile)),
        preview: reader.result as string
      };
      storeImagesToFileHandle.push(finalFileHandle);
    }
    catalog.catalogImages = storeImagesToFileHandle;
    return catalog;
  }
  public dataURItoBlob(picBytes, imageType){
    const byteString = window.atob(picBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for(let i = 0; i < byteString.length; i++){
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: imageType });
    return blob;
  }
}
