import {Component, OnInit} from '@angular/core';
import {initMap} from './geocode';

@Component({
  selector: 'app-geoCode',
  templateUrl: './getdirection.component.html',
  styleUrls: ['./getdirection.component.scss']
})
export class GetdirectionComponent implements OnInit {

  ngOnInit(): void {
    initMap();
  }
}
