import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-banners',
  templateUrl: './banners.component.html',
  styleUrls: ['./banners.component.scss']
})
export class BannersComponent implements OnInit {
  lat = 51.678418;
  lng = 7.809007;
  constructor() { }

  ngOnInit(): void {
  }

}
