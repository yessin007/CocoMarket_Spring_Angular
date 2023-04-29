import { Injectable } from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from '@angular/router';
import { Product } from '../classes/product';
import { ProductService } from './product.service';
import {ImageProcessingService} from "./image-processing.service";
import {Observable, of} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
	providedIn: 'root'
})
export class Resolver implements Resolve<Product> {
  
  public product: Product = {};

  constructor(
    private router: Router,
    public productService: ProductService, private imageProcessingService: ImageProcessingService, private route: ActivatedRoute
  ) {}

  // Resolver
 /* async resolvee(route: ActivatedRouteSnapshot): Promise<any> {
    await new Promise(resolve => setTimeout(resolve, 0));
    this.productService.getProductBySlug(route.params.slug).subscribe(product => {
      if(!product) { // When product is empty redirect 404
          this.router.navigateByUrl('/pages/404', {skipLocationChange: true});
      } else {
          this.product = this.imageProcessingService.createImages(product);
      }
    })
    return this.product;
  } */
 /* resolvewq(route: ActivatedRouteSnapshot) {
    this.productService.getProductBySlug(route.params.slug).subscribe(product => {
      if(!product) { // When product is empty redirect 404
        this.router.navigateByUrl('/pages/404', {skipLocationChange: true});
      } else {
        this.product = this.imageProcessingService.createImages(product);
      }
    })
    return this.product;
  } */
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Product> {
    const id = route.paramMap.get('productId');
    if (id){
      return this.productService.getProductById(id).pipe(
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
