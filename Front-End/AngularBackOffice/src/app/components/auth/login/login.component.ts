import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import {AuthService} from '../../../services/auth.service';
import {User} from '../../../models/User';

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

  constructor(private formBuilder: UntypedFormBuilder, private authService: AuthService) {
    this.createLoginForm();
    this.createRegisterForm();
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data;
    });
    if (this.authService.isLoggedIn()){
      this.isLoggedIn = true;
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

  onSubmit(): void {
    const { username, password } = this.form;
    console.log('clicked login');
    console.log(this.form.username);
    this.authService.login(this.form.username, this.form.password).subscribe({
      next: data => {
        // tslint:disable-next-line:no-shadowed-variable
        this.authService.currentUser.subscribe(data => {
          this.currentUser = data;
          console.log('login done');
        });
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        /*this.reloadPage();*/
      },
      error: err => {
        this.isLoginFailed = true;
        console.log('err login');
        console.log(err);
      }
    });
  }
  reloadPage(): void {
    window.location.reload();
  }
}
