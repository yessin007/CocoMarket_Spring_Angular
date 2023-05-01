import { Component, Injectable, OnInit } from '@angular/core';
import { GetCurrentLocation } from '../../../shared/services/GetCurrentLocation';
import { AddDelivery } from '../../../shared/services/AddDelivery';
import { Deliveries } from '../../../shared/services/Deliveries';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
  providers: [Deliveries, AddDelivery]
})
export class CheckoutComponent implements OnInit {
  lat: number;
  lng: number;
  delivery: Deliveries = new Deliveries();

  constructor(private getCurrentLocationn: GetCurrentLocation, private addDelivery: AddDelivery) {}
  isContentHidden = false;

  ngOnInit() {
    window.addEventListener('message', event => {
      if (event.origin !== 'https://storage.googleapis.com') {
        return;
      }
      console.log(event.data);
    });
  }

  getCurrentLocation() {
    this.getCurrentLocationn.getCurrentLocation().then((location) => {
      this.lat = location.coords.latitude;
      this.lng = location.coords.longitude;
      console.log(`lat: ${this.lat}, lng: ${this.lng}`);
    }).catch((err) => {
      console.log(err);
    });
  }
  hideContent() {
    this.isContentHidden = !this.isContentHidden;

  }

  onSubmit() {
    this.addDelivery.addDeliveryWithLocation(this.delivery, this.lat, this.lng).subscribe((resp) => {
        console.log(resp);
      });
    }
}
