import { Component, OnInit, Input } from '@angular/core';
import { LogoSlider } from '../../../shared/data/slider';

@Component({
  selector: 'app-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./logo.component.scss']
})
export class LogoComponent implements OnInit {

  constructor() { }

  @Input() logos: any[] = [];

  public LogoSliderConfig: any = LogoSlider;

  ngOnInit(): void {
  }

}
