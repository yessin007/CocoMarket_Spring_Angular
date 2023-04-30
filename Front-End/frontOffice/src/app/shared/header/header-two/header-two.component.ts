import { Component, OnInit, Input, HostListener } from '@angular/core';

@Component({
  selector: 'app-header-two',
  templateUrl: './header-two.component.html',
  styleUrls: ['./header-two.component.scss']
})
export class HeaderTwoComponent implements OnInit {

  @Input() class: string;
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
    if (number >= 150 && window.innerWidth > 400) {
      this.stick = true;
    } else {
      this.stick = false;
    }
  }

}
