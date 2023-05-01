import { Component, OnInit } from '@angular/core';
import {User} from "../../../shared/models/User";
import {UntypedFormGroup} from "@angular/forms";
import {AuthService} from "../../../shared/services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
<<<<<<< HEAD

	// tslint:disable-next-line:new-parens
	currentUser: User = new User;
=======
	currentUser: User = new User();
>>>>>>> main
	isLoggedIn = false;
	isLoginFailed = false;
	form: any = {
		username: null,
		password: null
	};
	constructor( private authService: AuthService, private router: Router, private getCurrentLocationn: GetCurrentLocation) {
		this.authService.currentUser.subscribe(data => {
			this.currentUser = data;
		});
		if (this.authService.isLoggedIn()){
			this.isLoggedIn = true;
			this.router.navigate(['']);
		}
	}

	ngOnInit() {
		this.authService.currentUser.subscribe(data => {
			this.currentUser = data;
		});
		if (this.authService.isLoggedIn()) {
			this.isLoggedIn = true;
		}
		console.log('login page open');
	}

  getCurrentLocation() {
    console.log(this.getCurrentLocationn.getCurrentLocation());
  }
	onSubmit(): void {
		// tslint:disable-next-line:indent
		const { username, password } = this.form;
		this.authService.login(this.form.username, this.form.password).subscribe({
			next: data => {
				this.authService.currentUser.subscribe(data => {
					this.currentUser = data;
				});
				this.isLoginFailed = false;
				this.isLoggedIn = true;
				this.reloadPage();// navigate to dashboard on success
			},
			error: err => {
				this.isLoginFailed = true;
			}
		});
	}

	reloadPage(): void {
		window.location.reload();
	}

	toForgetPage() {
		this.router.navigate(['pages/forget/password']);
	}
  }


