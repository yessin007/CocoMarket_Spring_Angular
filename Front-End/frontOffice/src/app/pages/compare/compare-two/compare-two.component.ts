import { Component, OnInit } from '@angular/core';
import { CompareSlider } from '../../../shared/data/slider';

@Component({
  selector: 'app-compare-two',
  templateUrl: './compare-two.component.html',
  styleUrls: ['./compare-two.component.scss']
})
export class CompareTwoComponent implements OnInit {

  constructor() { }

  public CompareSliderConfig: any = CompareSlider;

  ngOnInit(): void {
  }

}
