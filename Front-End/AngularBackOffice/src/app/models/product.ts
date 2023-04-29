import {FileHandle} from './FileHandle';

export class Product {
    productId: number;
    reference: string;
    title: string;
    description: string;
    quantity: number;
    model: File;
    video: string;
    brand: string;
    price: number;
    stock: number;
    dateOfPurchase: Date;
    discount: number;
    yearsOfWarranty: number;
    createdAt: Date;
    productCategory: string;
    image: FileHandle[];
    numberOfLikes?: number;
}
