import { Component, OnInit } from '@angular/core';
import {AgmMap} from "@agm/core";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  lat = 35.9038537;
  lng = 10.542212;
  constructor() { }

  ngOnInit(): void {
  }

  onChosenPos(event: google.maps.MapMouseEvent){
    console.log('Map clicked', event);

  }

}
