import {FileHandle} from './FileHandle';
export class  Store{
    storeId: number;
    storeName: string;
    contactInformation: number;
    storeDescription: string ;
    storeEmailAddress: string;
    link: string;
    storeLocations: string;
    category: string;
    storeImages: FileHandle[];
}
