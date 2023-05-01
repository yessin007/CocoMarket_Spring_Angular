import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Deliveries } from './Deliveries';

@Injectable({
    providedIn: 'root'
})
export class AddDelivery {

    readonly ADD_DELIVERY_API = 'http://localhost:9092/COCO/api/delivery/add_delivery';

    constructor(private httpClient: HttpClient) { }

    addDeliveryWithLocation(delivery: Deliveries, lat: number, lng: number) {
        delivery.clientLatitude = lat;
        delivery.clientLongitude = lng;

        return this.httpClient.post<Deliveries>(`${this.ADD_DELIVERY_API}/${lat}/${lng}`, delivery);
    }


}
