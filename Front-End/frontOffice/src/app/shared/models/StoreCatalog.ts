// @ts-ignore
import {FileHandleMal} from './FileHandleMal';


export class StoreCatalog{
    catalogId: number ;
    catalogName: string ;
    date: Date ;

    catalogDescription: string;
    catalogImages: FileHandleMal[];

    storeName?: string;
}
