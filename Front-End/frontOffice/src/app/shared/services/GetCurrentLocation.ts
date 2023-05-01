import {Injectable, Provider} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Deliveries} from './Deliveries';
import {response} from "express";
@Injectable({
    providedIn: 'root'
})
export class GetCurrentLocation {

    readonly GET_CURRENT_LOCATION = 'http://localhost:8083/kaddem/api/maps';
    constructor(private httpClient: HttpClient) {
    }
    lat: number;
    lng: number;
    delivery: Deliveries = new Deliveries();
    getCurrentLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
               this.lat = position.coords.latitude;
               this.lng = position.coords.longitude;
               console.log(`Latitude: ${this.lat}, Longitude: ${this.lng}`);
               this.delivery.clientLatitude = this.lat;
               this.delivery.clientLongitude = this.lng;
               const ASSIGN_LOCATION = `http://localhost:8083/kaddem/api/delivery/add_delivery/${this.lat}/${this.lng}`;

               this.setLatLngToProvider(ASSIGN_LOCATION, this.delivery).subscribe((resp) => {
                   console.log(resp);
               });
            });



        } else {
            console.log('Geolocation is not supported by this browser.');
        }

    }
    setLatLngToProvider(url: string,d : Deliveries) {
        return this.httpClient.post<Deliveries>(url, d);
    }



}
