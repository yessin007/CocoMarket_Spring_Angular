import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  constructor() { }

<<<<<<< HEAD
  ngOnInit(): void {
  }
=======
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
>>>>>>> main

}
