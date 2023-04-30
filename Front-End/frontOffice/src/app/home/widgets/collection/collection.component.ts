import { Component, OnInit, Input } from '@angular/core';
import { CollectionSlider } from '../../../shared/data/slider';

@Component({
  selector: 'app-collection',
  templateUrl: './collection.component.html',
  styleUrls: ['./collection.component.scss']
})
export class CollectionComponent implements OnInit {

  constructor() { }

  @Input() categories: any[];
  @Input() category: string;
  @Input() class: string;

   public CollectionSliderConfig: any = CollectionSlider;

  ngOnInit(): void {
  }

}
