import {Provider} from './provider';

export class Delivery {
    id: number;
    clientLatitude: number;
    clientLongitude: number;
    clientAddress: string;
    statut : string ;
    provider : Provider;
}
