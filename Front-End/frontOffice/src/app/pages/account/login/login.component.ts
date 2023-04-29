import { Component, OnInit } from '@angular/core';
import {GetCurrentLocation} from "../../../shared/services/GetCurrentLocation";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(private getCurrentLocationn: GetCurrentLocation) { }

  ngOnInit(): void {
  }
  getCurrentLocation() {
    console.log(this.getCurrentLocationn.getCurrentLocation());
  }

  }

