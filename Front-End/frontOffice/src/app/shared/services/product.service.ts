import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, startWith, delay } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { Product } from '../classes/product';

import { Order1 } from '../classes/order1';
import { ProductCart } from '../classes/productcart';
import {ImageProcessingService} from './image-processing.service';
import {Review} from '../classes/review';
import {CartItem} from "../classes/CartItem";
import {SmsPojo} from "../classes/SmsPojo";
import {RequestBaseService} from "../../services/RequestBaseService.service";
import {AuthService} from "../../services/auth.service";

const state = {
  products: JSON.parse(localStorage.products || '[]'),
  wishlist: JSON.parse(localStorage.wishlistItems || '[]'),
  compare: JSON.parse(localStorage.compareItems || '[]'),
  cart: JSON.parse(localStorage.cartItems || '[]')
};

@Injectable({
  providedIn: 'root'
})
export class ProductService extends RequestBaseService  {

  public Currency = { name: 'Dinar', currency: 'TND', price: 1 }; // Default Currency
  public OpenCart = false;
  public Products;
  public Product;

  public ProductCart;
  readonly GET_ALL_PRODUCTS_API_URL = 'http://localhost:8089/radhwen/api/product/getallproducts?searchKey=';
  readonly GET_PRODUCT_DETAILS_API_URL = 'http://localhost:8089/radhwen/api/product/getproductdetails/';

  readonly ADDTOCART = 'http://localhost:8089/radhwen/api/product/getproductdetails/';

  readonly ADD_REVIEW_TO_PRODUCT = 'http://localhost:8089/radhwen/api/product/affectreviewtoproduct/1/';

  readonly GET_ALL_PRODUCTS_API_URLL = 'http://localhost:8089/radhwen/api/product/getallproducts';



  ///////////////////////////////////////////////////////////////////////////////////////////////////


  readonly PRODUCTCART_API_URL = 'http://localhost:8089/radhwen/api/productCart/addproduct';
  readonly GET_ALL_PRODUCTSCART_API_URL = 'http://localhost:8089/radhwen/api/productCart/getallproducts';
  readonly DELETE_PRODUCTCART_API_URL = 'http://localhost:8089/radhwen/api/productCart/deleteproduct/';
  readonly GET_PRODUCTCART_DETAILS__API_URL = 'http://localhost:8089/radhwen/api/productCart/getproductdetails/';
  //////////////////////////////////////////////////////////////////////

  readonly CARTITEM = 'http://localhost:8089/radhwen/api/cart/cartItem';
  readonly GET_CART_ITEM_WITH_PRODUCTS = 'http://localhost:8089/radhwen/api/cart/getCartItemsWithProducts';
  readonly DELETE_CARTITEM = 'http://localhost:8089/radhwen/api/cart/delete_cartItem/';
  /////////////////////////////////////////////////////////////////////////

  readonly ADD_ORDERS = 'http://localhost:8089/radhwen/api/order1/add_order';
  readonly UPDATE_ORDERS = 'http://localhost:8089/radhwen/api/order/update_order';
  readonly GETALL_ORDERS = 'http://localhost:8089/radhwen/api/order/retrive_all_orders';
  readonly DELETE_ORDERS = 'http://localhost:8089/radhwen/api/order/delete_order/';
  readonly GET_ORDER_DETAILS_API_URL = 'http://localhost:8089/radhwen/api/order/retrive_order/';



  ///////////////////////////////////////////////////////////////////////////////////////
  // /*            SMS
  ///////////////////////////////////////////////////////////////////////////////////////////

  readonly SMS = 'localhost:9092/COCO/api/payement/sms/SubmitSms';



  // constructor(private authenticationService: AuthService, private http: HttpClient,
  //             private toastrService: ToastrService, private httpClient: HttpClient, private imageProcessingService: ImageProcessingService) {
  //   super( authenticationService, http);
  // }

  constructor(
      protected authenticationService: AuthService,
      protected http: HttpClient, private toastrService: ToastrService, private httpClient: HttpClient, private imageProcessingService: ImageProcessingService
  ) {
    super(authenticationService, http);
  }
  
  /* Order */


  sms(smsPojo: SmsPojo){
    const url = `localhost:9092/COCO/api/payement/sms/SubmitSms`;
    return this.http.post<SmsPojo>(url, smsPojo,{headers: this.getHeaders});
  }


  addOrder(order1: Order1): Observable<any> {
    // debugger
    return this.httpClient.post(this.ADD_ORDERS, order1);
  }

  getAllOrders(){
    return this.httpClient.get<Order1[]>(this.GETALL_ORDERS);
  }
  deleteOrder(orderId: number){
    return this.httpClient.delete(this.DELETE_ORDERS + orderId);
  }
  updateOrder(order1: FormData): Observable<any>{
    return this.httpClient.put(this.UPDATE_ORDERS, order1);
  }

  updateeOrder(order1Id: number): Observable<any>{
    return this.httpClient.put(this.UPDATE_ORDERS, order1Id);
  }





  getOrderDetails(orderId){
    return this.httpClient.get<Order1>(this.GET_ORDER_DETAILS_API_URL + orderId);
  }
  
  
  
  
  
  
  
  
  /* End Order */
  /*
    ---------------------------------------------
    ---------------  Product  -------------------
    ---------------------------------------------
  */

  // public addProductToCartItem(cartId, productId, quantity){
  //   return this.httpClient.post(this.CARTITEM + cartId , + '/' + productId , + '/' + quantity);
  // }
  public getCartItemsWithProducts(cartId: number): Observable<CartItem[]> {
    return this.httpClient.get<CartItem[]>(`${this.GET_CART_ITEM_WITH_PRODUCTS}/${cartId}`);
  }

  getProduct(productId: number): Observable<Product> {
    const url = `http://localhost:8089/radhwen/api/product/getproductdetails/${productId}`;
    return this.httpClient.get<Product>(url);
  }

  CartgetAllProducts(){
    return this.httpClient.get<Product[]>(this.GET_ALL_PRODUCTS_API_URLL);
  }

  public addProductToCartItem(cartId, productId, quantity){
    const url = `${this.CARTITEM}/${cartId}/${productId}/${quantity}`;
       return this.httpClient.post(url, null);
  }
  // addProductStore(storeId: number, productId: number): Observable<void> {
  //   const url = ${this.apiUrl}/affectproducttostore/${storeId}/${productId};
  //   return this.http.post<void>(url, null);


  // }

  public addProductCart(productCart: ProductCart): Observable<any> {
    // return this.httpClient.post<Product>(this.PRODUCT_API_URL, product);
    return this.httpClient.post(this.PRODUCTCART_API_URL , productCart );
  }
  // Product

  private get products(): Observable<Product[]> {
    this.Products = this.httpClient.get<Product[]>(this.GET_ALL_PRODUCTS_API_URL);
    return this.Products;
  }


  public addToCart(productId){
    return this.httpClient.get('http://localhost:8089/radhwen/api/cart/addToCart/' + productId );
  }


  public getCartDetails(){
    return this.httpClient.get('http://localhost:8089/radhwen/api/cart/getCartDetails');
  }


  /*public getAllProducts(){
    this.getproducts()
        .pipe(
            map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
        )
        .subscribe(
            (resp: Product[]) => {console.log(resp); },
            (error: HttpErrorResponse) => {console.log(error); }
        );
  } */

  // Get Products
  public get getProducts(): Observable<Product[]> {
    return this.products.pipe(
        map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
    );

  }

  // Get Products By Slug
  public getProductBySlug(slug: string): Observable<Product> {
    return this.products.pipe(map(items => {
      return items.find((item: any) => {
        return item.title.replace(' ', '-') === slug;
      });
    }));
  }
  public searchByKeyWord(seachKey: string = ''){
    return this.httpClient.get<Product[]>(this.GET_ALL_PRODUCTS_API_URL + seachKey);
  }
  public getProductById(productId): Observable<Product>{
    return  this.httpClient.get<Product>(this.GET_PRODUCT_DETAILS_API_URL + productId);
  }
  public reviewProduct(review: Review , productId){
    return  this.httpClient.post<Product>(this.ADD_REVIEW_TO_PRODUCT + productId, review);
  }


  public removeCartItemm(id: number) {
    return this.httpClient.delete(this.DELETE_CARTITEM + id);
  }

  /*
    ---------------------------------------------
    ---------------  Wish List  -----------------
    ---------------------------------------------
  */
  // Get Wishlist Items

  public get wishlistItems(): Observable<Product[]> {
    const itemsStream = new Observable(observer => {
      observer.next(state.wishlist);
      observer.complete();
    });
    return itemsStream as Observable<Product[]>;
  }
  // Add to Wishlist

  public addToWishlist(product): any {
    const wishlistItem = state.wishlist.find(item => item.id === product.id);
    if (!wishlistItem) {
      state.wishlist.push({
        ...product
      });
    }
    this.toastrService.success('Product has been added in wishlist.');
    localStorage.setItem('wishlistItems', JSON.stringify(state.wishlist));
    return true;
  }
  // Remove Wishlist items

  public removeWishlistItem(product: Product): any {
    const index = state.wishlist.indexOf(product);
    state.wishlist.splice(index, 1);
    localStorage.setItem('wishlistItems', JSON.stringify(state.wishlist));
    return true;
  }

  /*
    ---------------------------------------------
    -------------  Compare Product  -------------
    ---------------------------------------------
  */
  // Get Compare Items

  public get compareItems(): Observable<Product[]> {
    const itemsStream = new Observable(observer => {
      observer.next(state.compare);
      observer.complete();
    });
    return itemsStream as Observable<Product[]>;
  }
  // Add to Compare

  public addToCompare(product): any {
    const compareItem = state.compare.find(item => item.id === product.id);
    if (!compareItem) {
      state.compare.push({
        ...product
      });
    }
    this.toastrService.success('Product has been added in compare.');
    localStorage.setItem('compareItems', JSON.stringify(state.compare));
    return true;
  }
  // Remove Compare items

  public removeCompareItem(product: Product): any {
    const index = state.compare.indexOf(product);
    state.compare.splice(index, 1);
    localStorage.setItem('compareItems', JSON.stringify(state.compare));
    return true;
  }

  /*
    ---------------------------------------------
    -----------------  Cart  --------------------
    ---------------------------------------------
  */
  // Get Cart Items

  public get cartItems(): Observable<Product[]> {
    const itemsStream = new Observable(observer => {
      observer.next(state.cart);
      observer.complete();
    });
    return itemsStream as Observable<Product[]>;
  }
  // Add to Cart
  // public addToCart(product): any {
  //   const cartItem = state.cart.find(item => item.id === product.id);
  //   const qty = product.quantity ? product.quantity : 1;
  //   const items = cartItem ? cartItem : product;
  //   const stock = this.calculateStockCounts(items, qty);
  //
  //   if (!stock) { return false; }
  //
  //   if (cartItem) {
  //       cartItem.quantity += qty;
  //   } else {
  //     state.cart.push({
  //       ...product,
  //       quantity: qty
  //     });
  //   }
  //
  //   this.OpenCart = true; // If we use cart variation modal
  //   localStorage.setItem('cartItems', JSON.stringify(state.cart));
  //   return true;

  // }
  // Update Cart Quantity

  public updateCartQuantity(product: Product, quantity: number): Product | boolean {
    return state.cart.find((items, index) => {
      if (items.id === product.productId) {
        const qty = state.cart[index].quantity + quantity;
        const stock = this.calculateStockCounts(state.cart[index], quantity);
        if (qty !== 0 && stock) {
          state.cart[index].quantity = qty;
        }
        localStorage.setItem('cartItems', JSON.stringify(state.cart));
        return true;
      }
    });
  }
  // Calculate Stock Counts

  public calculateStockCounts(product, quantity) {
    const qty = product.quantity + quantity;
    const stock = product.stock;
    if (stock < qty || stock == 0) {
      this.toastrService.error('You can not add more items than available. In stock ' + stock + ' items.');
      return false;
    }
    return true;
  }
  // Remove Cart items

  public removeCartItem(product: Product): any {
    const index = state.cart.indexOf(product);
    state.cart.splice(index, 1);
    localStorage.setItem('cartItems', JSON.stringify(state.cart));
    return true;
  }

  // Total amount
  public cartTotalAmount(): Observable<number> {
    return this.cartItems.pipe(map((product: Product[]) => {
      return product.reduce((prev, curr: Product) => {
        let price = curr.price;
        if (curr.discount) {
          price = curr.price - (curr.price * curr.discount / 100);
        }
        return (prev + price * curr.quantity) * this.Currency.price;
      }, 0);
    }));
  }

  public getCartTotalAmount(cartItems: CartItem[]): number {
    let totalAmount = 0;
    for (let cartItem of cartItems) {
      let price = cartItem.product.price;
      if (cartItem.product.discount) {
        price = cartItem.product.price - (cartItem.product.price * cartItem.product.discount / 100);
      }
      totalAmount += price * cartItem.product.quantity;
    }
    return totalAmount;
  }

  /*
    ---------------------------------------------
    ------------  Filter Product  ---------------
    ---------------------------------------------
  */

  // Get Product Filter
 /* public filterProducts(filter: any): Observable<Product[]> {
    return this.products.pipe(map(product =>
      product.filter((item: Product) => {
        if (!filter.length) return true
        const Tags = filter.some((prev) => { // Match Tags
          if (item.tags) {
            if (item.tags.includes(prev)) {
              return prev
            }
          }
        })
        return Tags
      })
    ));
  } */

  // Sorting Filter
  public sortProducts(products: Product[], payload: string): any {

    if (payload === 'ascending') {
      return products.sort((a, b) => {
        if (a.productId < b.productId) {
          return -1;
        } else if (a.productId > b.productId) {
          return 1;
        }
        return 0;
      });
    } else if (payload === 'a-z') {
      return products.sort((a, b) => {
        if (a.title < b.title) {
          return -1;
        } else if (a.title > b.title) {
          return 1;
        }
        return 0;
      });
    } else if (payload === 'z-a') {
      return products.sort((a, b) => {
        if (a.title > b.title) {
          return -1;
        } else if (a.title < b.title) {
          return 1;
        }
        return 0;
      });
    } else if (payload === 'low') {
      return products.sort((a, b) => {
        if (a.price < b.price) {
          return -1;
        } else if (a.price > b.price) {
          return 1;
        }
        return 0;
      });
    } else if (payload === 'high') {
      return products.sort((a, b) => {
        if (a.price > b.price) {
          return -1;
        } else if (a.price < b.price) {
          return 1;
        }
        return 0;
      });
    }
  }

  /*
    ---------------------------------------------
    ------------- Product Pagination  -----------
    ---------------------------------------------
  */
  public getPager(totalItems: number, currentPage: number = 1, pageSize: number = 16) {
    // calculate total pages
    const totalPages = Math.ceil(totalItems / pageSize);

    // Paginate Range
    const paginateRange = 3;

    // ensure current page isn't out of range
    if (currentPage < 1) {
      currentPage = 1;
    } else if (currentPage > totalPages) {
      currentPage = totalPages;
    }

    let startPage: number, endPage: number;
    if (totalPages <= 5) {
      startPage = 1;
      endPage = totalPages;
    } else if (currentPage < paginateRange - 1){
      startPage = 1;
      endPage = startPage + paginateRange - 1;
    } else {
      startPage = currentPage - 1;
      endPage =  currentPage + 1;
    }

    // calculate start and end item indexes
    const startIndex = (currentPage - 1) * pageSize;
    const endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

    // create an array of pages to ng-repeat in the pager control
    const pages = Array.from(Array((endPage + 1) - startPage).keys()).map(i => startPage + i);

    // return object with all pager properties required by the view
    return {
      totalItems,
      currentPage,
      pageSize,
      totalPages,
      startPage,
      endPage,
      startIndex,
      endIndex,
      pages
    };
  }


  


}
