import { Component, OnInit, Input, HostListener } from '@angular/core';

@Component({
  selector: 'app-header-four',
  templateUrl: './header-four.component.html',
  styleUrls: ['./header-four.component.scss']
})
export class HeaderFourComponent implements OnInit {

  @Input() class = 'header-2 header-6';
  @Input() themeLogo = 'assets/images/icon/logo.png'; // Default Logo
  @Input() topbar = true; // Default True
  @Input() sticky = false; // Default false

  public stick = false;

  constructor() { }

  ngOnInit(): void {
  }

  // @HostListener Decorator
  @HostListener('window:scroll', [])
  onWindowScroll() {
    const number = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    if (number >= 150 && window.innerWidth  > 400) {
      this.stick = true;
    } else {
      this.stick = false;
    }
  }

}
