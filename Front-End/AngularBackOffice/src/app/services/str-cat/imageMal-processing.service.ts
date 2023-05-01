import { Injectable } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
// @ts-ignore
import {FileHandleMal} from '../../models/FileHandleMal';
import {StoreCatalog} from '../../models/storeCatalog';

@Injectable({
  providedIn: 'root'
})
export class ImageMalProcessingService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImagesMal(catalog: StoreCatalog) {
    const catalogImages: any[] = catalog.catalogImages;
    const catalogImagesToFileHandle: FileHandleMal[] = [];
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < catalogImages.length; i++){
      const imageFileData = catalogImages[i];
      const imageBlobMAL = this.dataURItoBlobMal(imageFileData.picByte, imageFileData.type);
      const imageFileMal = new File([imageBlobMAL], imageFileData.name, {type: imageFileData.type});
      const reader = new FileReader();
      reader.readAsDataURL(imageFileMal);
      // @ts-ignore
      // @ts-ignore
      const finalFileHandle: FileHandle = {
        filemal: imageFileMal,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFileMal)),
        preview: reader.result as string
      };
      catalogImagesToFileHandle.push(finalFileHandle);
    }
    catalog.catalogImages = catalogImagesToFileHandle;
    return catalog;
  }
  public dataURItoBlobMal(pictBytes, imageMalType){
    const byteString = window.atob(pictBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++){
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: imageMalType });
    return blob;
  }
}
