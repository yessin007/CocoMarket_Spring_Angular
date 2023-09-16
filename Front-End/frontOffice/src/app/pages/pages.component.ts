import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {AddDelivery} from '../shared/services/AddDelivery';
import {Deliveries} from '../shared/services/Deliveries';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.scss'],
  providers: [Deliveries, AddDelivery]

})
export class PagesComponent implements OnInit {

  public url: any;

  constructor(private router: Router) {
    this.router.events.subscribe((event) => {
          if (event instanceof NavigationEnd) {
            this.url = event.url;
          }
    });
  }

  ngOnInit(): void {
  }

}
