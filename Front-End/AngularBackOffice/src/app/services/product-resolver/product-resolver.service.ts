import { Injectable } from '@angular/core';
import {Product} from '../../models/product';
import {Observable, of} from 'rxjs';
import {ProductService} from '../product/product.service';
import {map} from 'rxjs/operators';
import {ImageProcessingService} from '../image-processing.service';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ProductResolverService implements Resolve<Product>{

  constructor(private productService: ProductService, private imageProcessingService: ImageProcessingService) { }
  product: Product = new Product();
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product> {
   const id = route.paramMap.get('productId');
   if (id){
        return this.productService.getProductDetails(id).pipe(
            map(p => this.imageProcessingService.createImages(p))
        );
   }else {
      return of(this.getProductDetail());
    }
  }

  getProductDetail(){
    return this.product;
  }

}
