import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../shared/services/auth.service";
import {Router} from '@angular/router';
import {User} from "../../../shared/models/User";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

	rform: any ={
		name: null,
		lastName: null,
		username: null,
		email: null,
		password: null,
	};
	isLoggedIn = false;
	currentUser: User = new User;
	constructor( private authService: AuthService, private router: Router) {
		this.authService.currentUser.subscribe(data => {
			this.currentUser = data;
		});
		if (this.authService.isLoggedIn()){
			this.isLoggedIn = true;
			this.router.navigate(['/dashboard/default']);
		}
	}

  ngOnInit(): void {
  }

	onRegister(): void {
		this.authService.register(this.rform.username, this.rform.name, this.rform.lastName, this.rform.email, this.rform.password)
			.subscribe(
				response => {
					console.log(response);
					// handle successful registration response here
					console.info("Registration is successful complete! check your mail for the verification");
					alert("Registration is successful complete! check your mail for the verification");
					this.router.navigate(['pages/login']);
				},
				error => {
					console.error(error);
					// handle registration error here
				}
			);
	}


	reloadPage(): void {
		window.location.reload();
	}
}
