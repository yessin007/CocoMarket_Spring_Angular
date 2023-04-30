import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-footer-four',
  templateUrl: './footer-four.component.html',
  styleUrls: ['./footer-four.component.scss']
})
export class FooterFourComponent implements OnInit {

  @Input() class = 'footer-light'; // Default class
  @Input() themeLogo = 'assets/images/icon/logo.png'; // Default Logo

  public today: number = Date.now();

  constructor() { }

  ngOnInit(): void {
  }

}
