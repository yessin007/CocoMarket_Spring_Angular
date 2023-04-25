import { Component } from '@angular/core';
import {ProviderLocation} from "../../../models/providerLocation";
import {ProviderService} from "../../../services/provider/provider.service";
import {response} from "express";

@Component({
  selector: 'app-provider-location',
  templateUrl: './provider-location.component.html',
  styleUrls: ['./provider-location.component.scss']
})
export class ProviderLocationComponent {


  constructor(private providerService: ProviderService) {
  }
  setLatLngToProvider(id) {
    this.providerService.setLatLngToProvider(id).subscribe();
  }
}
