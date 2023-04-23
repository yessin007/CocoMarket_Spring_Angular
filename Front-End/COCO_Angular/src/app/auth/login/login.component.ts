import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {User} from "../../models/User";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: User = new User;
  name!:string;
  constructor(private authService: AuthService) {
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data;
    })
    if(this.authService.isLoggedIn()){
      this.isLoggedIn = true;
    }
  }

  ngOnInit(): void {
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data;
    })
    if (this.authService.isLoggedIn()) {
      this.isLoggedIn = true;

    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe({
      next: data => {
        this.authService.currentUser.subscribe(data => {
          this.currentUser = data;
        })
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        //this.u = this.storageService.getUser();
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });
    //console.log(this.currentUser.username);
  }

  reloadPage(): void {
    //this.authService.logOut();
    window.location.reload();
  }
}
