import {FileHandle} from './FileHandle';
import {StoreCatalog} from "./storeCatalog";
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
    storeCatalog: StoreCatalog[];
}
