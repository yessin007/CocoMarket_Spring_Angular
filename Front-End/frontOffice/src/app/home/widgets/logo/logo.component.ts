import { Component, OnInit, Input } from '@angular/core';
import { LogoSlider } from '../../../shared/data/slider';
import {Router} from "@angular/router";

@Component({
  selector: 'app-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./logo.component.scss']
})
export class LogoComponent implements OnInit {

  constructor(private rr: Router) { }
  
  @Input() logos: any[] = [];
  public LogoSliderConfig: any = LogoSlider;

  ngOnInit(): void {
  }
 // redirectToStore
  gotoElectronicStoreProducts(id){
    this.rr.navigate(['/home/electronics', {storeId: id}]);
  }
  gotoFashionStoreProducts(id){
    this.rr.navigate(['/home/fashion-2', {storeId: id}]);
  }

}
