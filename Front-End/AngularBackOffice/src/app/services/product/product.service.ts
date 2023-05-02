import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Product} from '../../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  readonly PRODUCT_API_URL = 'http://localhost:8089/maram/api/product/addproduct';
  readonly GET_ALL_PRODUCTS_API_URL = 'http://localhost:9092/COCO/api/product/getallproducts?searchKey=';
  readonly DELETE_PRODUCT_API_URL = 'http://localhost:9092/COCO/api/product/deleteproduct/';
  readonly GET_PRODUCT_DETAILS__API_URL = 'http://localhost:9092/COCO/api/product/getproductdetails/';
  readonly GET_TOTAL_PRODUCTS_PRICE = 'http://localhost:9092/COCO/api/product/gettotalpriceproducts';
  readonly GET_TOP_FIVE_MOST_LIKED_PRODUCTS = 'http://localhost:9092/COCO/api/product/topfivemostlikedproducts';
  readonly GET_NUMBER_OF_LIKES_OF_PRODUCT = 'http://localhost:9092/COCO/api/product/getnumberoflikes/';
  readonly GET_AVERAGE_LIKES_OF_PRODUCT = 'http://localhost:9092/COCO/api/product/getaveragelikesofproduct/';

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

  getTotalProductsPrice(): Observable<number>{
    return this.httpClient.get<number>(this.GET_TOTAL_PRODUCTS_PRICE);
  }
  getToopFiveMostLikeProducts(): Observable<Product[]>{
    return this.httpClient.get<Product[]>(this.GET_TOP_FIVE_MOST_LIKED_PRODUCTS);
  }
  getNumberOfLikesOfProduct(productId: number): Observable<number> {
    return this.httpClient.get<number>(this.GET_NUMBER_OF_LIKES_OF_PRODUCT + productId);
  }
  public getAverageLikesOfProduct(productId){
    return this.httpClient.get<number>(this.GET_AVERAGE_LIKES_OF_PRODUCT + productId);
  }

    getProduct(id: number): Observable<Product> {
    const url = `${this.GET_PRODUCT_DETAILS__API_URL}/${id}`;
    return this.httpClient.get<Product>(url);

  }

}
