import {Product} from "./product";
import {Cart} from "./cart";


export class CartItem {
    id: number;
    cart: Cart;
    product: Product;
    quantity: number;

}