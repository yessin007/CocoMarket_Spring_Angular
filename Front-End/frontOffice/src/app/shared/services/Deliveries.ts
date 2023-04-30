import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class Deliveries {
id: number;
    clientAddress: string;
    clientLatitude: number;
    clientLongitude: number;

}
