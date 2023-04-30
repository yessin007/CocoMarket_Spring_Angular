import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import {AuthService} from '../../../services/auth.service';
import {User} from '../../../models/User';
import {Router} from '@angular/router';
import {RegisterRequest} from '../../../models/RegisterRequest';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

	public loginForm: UntypedFormGroup;
	public registerForm: UntypedFormGroup;
	public active = 1;
	// tslint:disable-next-line:new-parens
	currentUser: User = new User;
	isLoggedIn = false;
	isLoginFailed = false;
	form: any = {
		username: null,
		password: null
	};
	rform: any = {
		name: null,
		lastName: null,
		username: null,
		email: null,
		password: null,
	};

	constructor(private formBuilder: UntypedFormBuilder, private authService: AuthService, private router: Router) {
		this.createLoginForm();
		this.createRegisterForm();
		this.authService.currentUser.subscribe(data => {
			this.currentUser = data;
		});
		if (this.authService.isLoggedIn()){
			this.isLoggedIn = true;
			this.router.navigate(['/dashboard/default']);
		}
	}

	owlcarousel = [
		{
			title: 'Welcome to Multikart',
			desc: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy.',
		},
		{
			title: 'Welcome to Multikart',
			desc: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy.',
		},
		{
			title: 'Welcome to Multikart',
			desc: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy.',
		}
	];
	owlcarouselOptions = {
		loop: true,
		items: 1,
		dots: true
	};

	createLoginForm() {
		this.loginForm = this.formBuilder.group({
			userName: [''],
			password: [''],
		});
	}
	createRegisterForm() {
		this.registerForm = this.formBuilder.group({
			userName: [''],
			password: [''],
			confirmPassword: [''],
		});
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

	// tslint:disable-next-line:indent
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
				this.reloadPage(); // navigate to dashboard on success
			},
			error: err => {
				this.isLoginFailed = true;
			}
		});
	}
	onRegister(): void {
			// console.log("pass",this.rform.password);
			// const { Rusername, Rpassword, Rname, RlastName, Remail} = {this.rform.user};
			this.authService.register(this.rform.username, this.rform.password, this.rform.name, this.rform.lastName, this.rform.email)
				.subscribe(
					response => {
						console.log(response);
						// handle successful registration response here
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
