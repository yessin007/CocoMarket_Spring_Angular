import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-footer-three',
  templateUrl: './footer-three.component.html',
  styleUrls: ['./footer-three.component.scss']
})
export class FooterThreeComponent implements OnInit {

  @Input() class: string; // Default class
  @Input() mainFooter = true; // Default true
  @Input() subFooter = false; // Default false
  @Input() themeLogo = 'assets/images/icon/logo.png'; // Default Logo

  public today: number = Date.now();

  constructor() { }

  ngOnInit(): void {
  }

}
