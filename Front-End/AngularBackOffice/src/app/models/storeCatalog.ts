// @ts-ignore
import {FileHandleMal} from './FileHandleMal';
import {Store} from './store';

export class StoreCatalog{
    catalogId: number ;
    catalogName: string ;
    date: Date ;

    catalogDescription: string;
    catalogImages: FileHandleMal[];

    storeName?: string;
    stores: Store[];
}
