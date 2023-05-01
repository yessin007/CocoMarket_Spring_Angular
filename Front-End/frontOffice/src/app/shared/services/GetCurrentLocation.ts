import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class GetCurrentLocation {

    constructor() { }

    getCurrentLocation(): Promise<GeolocationPosition> {
        return new Promise((resolve, reject) => {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(
                    position => {
                        resolve(position);
                    },
                    error => {
                        reject(error);
                    }
                );
            } else {
                reject('Geolocation is not supported by this browser.');
            }
        });
    }
}
