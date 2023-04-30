import {Component, Injectable, OnInit} from '@angular/core';
import {GetCurrentLocation} from "../../../shared/services/GetCurrentLocation";
import {AddDelivery} from "../../../shared/services/AddDelivery";
import {Deliveries} from "../../../shared/services/Deliveries";
import {response} from "express";

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
  providers: [Deliveries] // add the provider here
})
export class CheckoutComponent implements OnInit {

  constructor(private getCurrentLocationn: GetCurrentLocation, private addDeliveries: AddDelivery) {}
  isContentHidden = false;

  delivery : Deliveries = new  Deliveries();


    ngOnInit() {
      window.addEventListener('message', event => {
        // Check that the event is from the correct origin
        if (event.origin !== 'https://storage.googleapis.com') {
          return;
        }

        // Handle the message from the iframe here
        console.log(event.data);
      });
    }

  hideContent() {
      this.isContentHidden = !this.isContentHidden;

  }
  getCurrentLocation() {
    console.log(this.getCurrentLocationn.getCurrentLocation());
  }

  onSubmit(){

    this.addDeliveries.addDelivery(this.delivery).subscribe((resp) => {
      console.log(resp);
    });
  }



}
