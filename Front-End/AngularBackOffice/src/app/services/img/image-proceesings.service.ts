import {Injectable} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {Product} from '../../models/product';
import {FileHandle} from '../../models/FileHandle';
import {StoreCatalog} from '../../models/storeCatalog';
import {Store} from "../../models/store";

@Injectable({
  providedIn: 'root'
})
export class ImageProceesingsService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImages(storeCatalog: Store) {
    const storeImages: any[] = storeCatalog.storeImages;
    const catalogImagesToFileHandle: FileHandle[] = [];
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < storeImages.length; i++){
      const imageFileData = storeImages[i];
      const imageBlob = this.dataURItoBlob(imageFileData.picByte, imageFileData.type);
      const imageFile = new File([imageBlob], imageFileData.name, {type: imageFileData.type});
      const reader = new FileReader();
      reader.readAsDataURL(imageFile);
      const finalFileHandle: FileHandle = {
        filefile: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile)),
        preview: reader.result as string
      };
      catalogImagesToFileHandle.push(finalFileHandle);
    }
    storeCatalog.storeImages = catalogImagesToFileHandle;
    return storeCatalog;
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
