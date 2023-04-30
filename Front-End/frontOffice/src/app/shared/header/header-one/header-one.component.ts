import { Component, OnInit, Input, HostListener } from '@angular/core';
import {User} from '../../models/User';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-header-one',
  templateUrl: './header-one.component.html',
  styleUrls: ['./header-one.component.scss']
})
export class HeaderOneComponent implements OnInit {

  @Input() class: string;
  @Input() themeLogo = 'assets/images/icon/CocoLogoM.png'; // Default Logo
  @Input() topbar = true; // Default True
  @Input() sticky = false; // Default false

  public stick = false;
	currentUser: User = new User;
	isLoggedIn = false;
	isLoginFailed = false;

  constructor(private authService: AuthService, private router: Router) {
	  this.authService.currentUser.subscribe(data => {
		  this.currentUser = data;
	  });
	  if (this.authService.isLoggedIn()){
		  this.isLoggedIn = true;
	  }
  }

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

	logout() {
		this.authService.logOut();
		window.location.reload();
	}
}
