import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../shared/services/auth.service";
import {Router} from "@angular/router";
import {User} from '../../../shared/models/User';
import {AgmMap} from "@agm/core";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

	currentUser: User = new User;
	isLoggedIn = false;
  lat = 35.9038537;
  lng = 10.542212;
  constructor() { }

	rform: any ={
		name: null,
		lastName: null,
		username: null,
		email: null,
		cin: null,
		dayOfBirth: new Date,
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
		const date = new Date(this.currentUser.dayOfBirth);
		const isoDate = date.toISOString().slice(0, 10); // extract yyyy-mm-dd portion
		this.rform.dayOfBirth = isoDate;
		// console.info(this.currentUser.dayOfBirth + ' date ' + date + ' isoDate ' + isoDate);
		this.rform.cin = this.currentUser.cin;

		this.aform.address = this.currentUser.address;
		this.aform.zipCode = this.currentUser.zipCode;
		console.log('zipCode: '+this.aform.zipCode + ' from ' + this.currentUser.zipCode);
		this.aform.city = this.currentUser.city;
		console.log('city' +this.aform.city +'from '+ this.currentUser.city);
		this.aform.country = this.currentUser.country;
		console.log('country' +this.aform.country +' from' +this.currentUser.country);
	}


	onSubmit() {
		console.log("test edit");
	}
  onChosenPos(event: google.maps.MapMouseEvent){
    console.log('Map clicked', event);

  }
}
