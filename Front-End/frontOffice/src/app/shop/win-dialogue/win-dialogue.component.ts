import {Component, Inject, Input, OnInit} from '@angular/core';
import {Product} from "../../shared/classes/product";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {ProductService} from "../../shared/services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "../../shared/classes/subscription";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-win-dialogue',
  templateUrl: './win-dialogue.component.html',
  styleUrls: ['./win-dialogue.component.scss']
})
export class WinDialogueComponent implements OnInit{
  @Input() prize: Product;
  @Input() sub: Subscription;
  ngOnInit(): void {
    console.log(this.prize);
    console.log(this.sub);
  }
  constructor(public dialog: MatDialog, public productService: ProductService, private router: Router,
              @Inject(MAT_DIALOG_DATA) public data: { prize: Product, sub: Subscription }) {
    this.prize = data.prize;
    this.sub = data.sub;
  }
  public deleteProduct(){
    this.productService.deleteSub(this.sub.subscriptionId).subscribe(() => console.log('sub deleted succ'));
    this.router.navigateByUrl('/home/fashion', { skipLocationChange: true });
  }


}
