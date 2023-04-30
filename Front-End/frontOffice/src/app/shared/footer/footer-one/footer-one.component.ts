import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-footer-one',
  templateUrl: './footer-one.component.html',
  styleUrls: ['./footer-one.component.scss']
})
export class FooterOneComponent implements OnInit {

  @Input() class = 'footer-light'; // Default class
  @Input() themeLogo = 'assets/images/icon/logo.png'; // Default Logo
  @Input() newsletter = true; // Default True

  public today: number = Date.now();

  constructor() { }

  ngOnInit(): void {
  }

}
