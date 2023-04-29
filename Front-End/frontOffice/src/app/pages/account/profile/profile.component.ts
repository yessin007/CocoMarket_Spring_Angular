import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../shared/services/auth.service";
import {Router} from "@angular/router";
import {User} from '../../../shared/models/User';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

	currentUser: User = new User;
	isLoggedIn = false;
	rform:any = {
		username: '',
        firstname: '',
        lastname: '',
        email: '',
        password: ''
	};
	aform: any = {
		address: null,
		zipCode: null,
        city: null,
        country: null,
	};
	constructor( private authService: AuthService, private router: Router) {
		this.authService.currentUser.subscribe(data => {
			this.currentUser = data;
		});
		if (this.authService.isLoggedIn()){
			this.isLoggedIn = true;
		}
		else this.router.navigate(['']);
	}

  ngOnInit(): void {
		this.rform.name = this.currentUser.name;
		this.rform.lastName = this.currentUser.lastName;
		this.rform.username = this.currentUser.username;
		this.rform.email = this.currentUser.email;
		console.info(this.currentUser.dayOfBirth + ' is ' + this.rform.dayOfBirth);
		// this.rform.dayOfBirth = this.currentUser.dayOfBirth;
	  this.rform.dayOfBirth = new Date(this.currentUser.dayOfBirth);
	  this.rform.cin = this.currentUser.cin;
  }

	onSubmit() {
		console.log("test edit");
	}

}
