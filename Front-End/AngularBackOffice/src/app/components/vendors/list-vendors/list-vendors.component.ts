import { Component, OnInit } from '@angular/core';
import { vendorsDB } from '../../../shared/tables/vendor-list';
import {Store} from '../../../models/store';
import {TableService} from '../../../shared/service/table.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-vendors',
  templateUrl: './list-vendors.component.html',
  styleUrls: ['./list-vendors.component.scss']
})
export class ListVendorsComponent implements OnInit {
  public vendors = [];
  stores: Store[] = [];

  constructor(public service: TableService) {
    this.vendors = vendorsDB.data;
  }

  public settings = {
    actions: {
      position: 'right'
    },
    columns: {
      vendor: {
        title: 'Vendor',
        type: 'html',
      },
      products: {
        title: 'Products'
      },
      store_name: {
        title: 'Store Name'
      },
      date: {
        title: 'Date'
      },
      balance: {
        title: 'Wallet Balance',
      },
      revenue: {
        title: 'Revenue',
      }
    },
  };

  ngOnInit() {
  }

}
