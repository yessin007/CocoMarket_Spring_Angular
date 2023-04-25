import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Product} from '../../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  readonly PRODUCT_API_URL = 'http://localhost:8089/radhwen/api/product/addproduct';
  readonly GET_ALL_PRODUCTS_API_URL = 'http://localhost:8089/radhwen/api/product/getallproducts';
  readonly DELETE_PRODUCT_API_URL = 'http://localhost:8089/radhwen/api/product/deleteproduct/';
  readonly GET_PRODUCT_DETAILS__API_URL = 'http://localhost:8089/radhwen/api/product/getproductdetails/';


  constructor(private httpClient: HttpClient) { }
  addProduct(product: FormData): Observable<any> {
    // return this.httpClient.post<Product>(this.PRODUCT_API_URL, product);
    return this.httpClient.post(this.PRODUCT_API_URL, product);
  }
  getAllProducts(){
    return this.httpClient.get<Product[]>(this.GET_ALL_PRODUCTS_API_URL);
  }
  deleteProduct(productId: number){
    return this.httpClient.delete(this.DELETE_PRODUCT_API_URL + productId);
  }
  getProductDetails(productId){
    return this.httpClient.get<Product>(this.GET_PRODUCT_DETAILS__API_URL + productId);
  }

}
