import {FileHandle} from './FileHandle';

export class Product {
    productId: number;
    reference: string;
    productName: string;
    description: string;
    quantity: number;
    images: string;
    model: File;
    video: string;
    brand: string;
    price: number;
    dateOfPurchase: Date;
    discount: number;
    yearsOfWarranty: number;
    createdAt: Date;
    productCategory: string;
    productImages: FileHandle[];

}
