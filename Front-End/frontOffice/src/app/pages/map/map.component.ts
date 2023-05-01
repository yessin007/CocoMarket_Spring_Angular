import {Component, AfterViewInit, OnInit} from '@angular/core';
import * as L from 'leaflet';
import {initMap} from './script';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {
  ngOnInit(): void {
    initMap();
  }
}
