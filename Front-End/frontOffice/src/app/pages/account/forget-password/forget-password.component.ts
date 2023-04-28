import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../shared/services/auth.service';
import {flash} from "ng-animate";

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit {
	userFaild = false;
	demDone = false;
	form: any = {
		email: null
	};
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

	onDemResetPassword(): void {
		const email = this.form.email; // replace with your email input value
		this.authService.demResetPassword(email).subscribe(
			response => {
				if (response.status === 200) {
					console.log(response.status); // should print 200 if email is found
					this.demDone = true;
				} else if (response.status === 440) {
					console.log(response.status); // should print 440 if email is not found
					// handle email not found error here
					this.userFaild = true;
				}
			},
			error => {
				console.error(error);

				// handle error here
			}
		);
	}

	onSubmit() {
	  console.log(this.form.email);
		this.onDemResetPassword();
	}
}
