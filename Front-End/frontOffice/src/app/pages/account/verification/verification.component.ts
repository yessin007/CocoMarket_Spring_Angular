import { Component } from '@angular/core';
import {AuthService} from "../../../shared/services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.scss']
})
export class VerificationComponent {

    rform: any ={
        code: null,
        email: null,
    };
    constructor(private auth: AuthService, private router: Router) {
    }

    onSubmit() {
        this.auth.verifAccount(this.rform.email, this.rform.code).subscribe((response: any) => {
            console.log(response);
            if (response.message === 'done') {
            } else {
                console.error(response.error);
                // Handle error
            }
        }, (error: any) => {
            console.error(error);
            // Handle error
        });
        alert('Account verified successfully! You can now login');
        this.router.navigate(['pages/login']);


    }
}
