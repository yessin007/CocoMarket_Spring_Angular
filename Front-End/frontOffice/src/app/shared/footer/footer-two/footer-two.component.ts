import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-footer-two',
  templateUrl: './footer-two.component.html',
  styleUrls: ['./footer-two.component.scss']
})
export class FooterTwoComponent implements OnInit {

  @Input() class: string;
  @Input() themeLogo = 'assets/images/icon/logo.png'; // default Logo
  @Input() mainFooter = true; // Default true
  @Input() subFooter = false; // Default false

  public today: number = Date.now();

  constructor() { }

  ngOnInit(): void {
  }

}
