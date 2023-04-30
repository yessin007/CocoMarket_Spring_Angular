import {Injectable, Provider} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Deliveries} from './Deliveries';
import {Observable} from 'rxjs';
@Injectable({
    providedIn: 'root'
})
export class AddDelivery {
    readonly ADD_DELIVERY = 'http://localhost:8083/kaddem/api/delivery/add_delivery';

    constructor(private httpClient: HttpClient) {
    }

    addDelivery(delivery: Deliveries): Observable<Deliveries> {
        return this.httpClient.post<Deliveries>(this.ADD_DELIVERY, delivery);
    }

}
