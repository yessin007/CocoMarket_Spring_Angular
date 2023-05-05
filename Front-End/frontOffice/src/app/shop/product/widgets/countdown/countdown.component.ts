import { Component, OnInit, Input } from '@angular/core';
import {ProductService} from "../../../../shared/services/product.service";
import {Product} from "../../../../shared/classes/product";
import {Subscription} from "../../../../shared/classes/subscription";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {SubDialogueComponent} from "../../../sub-dialogue/sub-dialogue.component";
import {WinDialogueComponent} from "../../../win-dialogue/win-dialogue.component";
import {ImageProcessingService} from "../../../../shared/services/image-processing.service";

@Component({
  selector: 'app-countdown',
  templateUrl: './countdown.component.html',
  styleUrls: ['./countdown.component.scss']
})
export class CountdownComponent implements OnInit {
  
  @Input() date: string;
  @Input() sub: Subscription;
  @Input() product: Product;

  public timerdate;
  public now;
  public prize: Product = {};
  public win = false;
  constructor(private productService: ProductService,  public dialog: MatDialog, private imageProcessingService: ImageProcessingService) {
    window.setInterval(() => {
      this.now = Math.trunc(new Date().getTime() / 1000)
    }, 1000)
  }

  ngOnInit(): void {
    this.timerdate = Math.trunc(new Date(this.date).getTime() / 1000);
    this.now = Math.trunc(new Date().getTime() / 1000);
    this.checkAllZero();
    //this.congratsPrize();
    window.setInterval(() => {
      if (this.timerReachedZero()) {
        this.win = true;
      }
    }, 1000);
  }
  checkAllZero() {
    if (this.days < 0 || this.hours < 0 || this.minutes < 0 || this.seconds < 0) {
     this.win = true;

    }
  }

  /// make it return boolean so it can help you tranform the timer  to button to check prize
  get seconds() {
    const seconds = (this.timerdate - this.now) % 60;
    return seconds;
  }
  get minutes() {
    return Math.trunc((this.timerdate - this.now) / 60) % 60  -1 ;

  }
  get hours() {
    return Math.trunc((this.timerdate - this.now) / 60 / 60) % 24 ;
  }
  get days() {
    return Math.trunc((this.timerdate - this.now) / 60 / 60 / 24)  ;
  }

  timerReachedZero() {
    return this.days <= 0 && this.hours <= 0 && this.minutes <= 0 && this.seconds <= 0;
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '800px';
    dialogConfig.height = '600px';
    dialogConfig.position = {
      left: '30%',
      top: '12%'
    };
    dialogConfig.data = { prize: this.imageProcessingService.createImages(this.prize), sub: this.sub };
    const dialogRef = this.dialog.open(WinDialogueComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Win Dialog result: ${result}`);
    });
  }

  public congratsPrize(){
      this.productService.winPrize(this.sub.subscriptionId).subscribe((resp) => {
        this.prize = resp;
        console.log(resp);
        this.openDialog();
      }); }


}
