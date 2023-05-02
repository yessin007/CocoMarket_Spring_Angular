// Products
import {SafeUrl} from "@angular/platform-browser";

export class ProductCart {

    collection?: any[];
    productId?: number;
    reference?: string;
    title?: string;
    description?: string;
    quantity?: number;
    model?: File;
    video?: string;
    brand?: string;
    price?: number;
    stock?: number;
    dateOfPurchase?: Date;
    discount?: number;
    yearsOfWarranty?: number;
    createdAt?: Date;
    productCategory?: string;
    image?: FileHandle[];
}

export interface FileHandle{
    filefile?: File;
    url?: SafeUrl;
    preview?: string;
}
