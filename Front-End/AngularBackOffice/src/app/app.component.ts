import { Component } from '@angular/core';
import {AuthService} from './services/auth.service';
import {User} from './models/User';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'multikart-backend';
  // tslint:disable-next-line:new-parens
  currentUser: User = new User;
  constructor( private authService: AuthService) {
    this.authService.currentUser.subscribe(data => {
      this.currentUser = data;
    });
  }
  // tslint:disable-next-line:use-lifecycle-interface
  ngOnInit() {
    //console.log("Hello");
  }
}
