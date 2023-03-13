import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-google',
  templateUrl: './google.component.html',
  styleUrls: ['./google.component.css']
})
export class GoogleComponent implements OnInit {

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }

  login(){
    this.http.post("http://localhost:9092/COCO/api/v1/auth/google", {}).subscribe(response => {
      console.log(response);
    });
  }



}
