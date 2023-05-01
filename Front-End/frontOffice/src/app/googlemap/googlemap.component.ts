import {Component, OnInit} from '@angular/core';
import {initMap} from "../pages/map/script";

@Component({
  selector: 'app-googlemap',
  templateUrl: './googlemap.component.html',
  styleUrls: ['./googlemap.component.scss']
})
export class GooglemapComponent implements OnInit{
  ngOnInit(): void {
    initMap();
  }

}
