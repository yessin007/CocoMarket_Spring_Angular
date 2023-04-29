import {ChangeDetectorRef, Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {TableService} from "../../../shared/service/table.service";
import {DecimalPipe} from "@angular/common";
import {Observable} from "rxjs";
import {LISTPAGEDB, ListPagesDB} from "../../../shared/tables/list-pages";
import {Delivery} from "../../../models/delivery";
import {ActivatedRoute, Router} from "@angular/router";
import {DeliveriesService} from "../../../services/deliveries.service";
import {NgbdSortableHeader, SortEvent} from "../../../shared/directives/NgbdSortableHeader";

@Component({
  selector: 'app-delivery-component',
  templateUrl: './delivery-component.component.html',
  styleUrls: ['./delivery-component.component.scss'],
  providers: [TableService, DecimalPipe]
})
export class DeliveryComponentComponent implements OnInit{
  public selected = [];
  public tableItem$: Observable<ListPagesDB[]>;
  public searchText;
  total$: Observable<number>;
  deliveryList: Delivery[] = [];

  constructor(public service: TableService, private deliveryService: DeliveriesService, private route : Router) {
    this.tableItem$ = service.tableItem$;
    this.total$ = service.total$;
    this.service.setUserData(LISTPAGEDB)
  }

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    this.headers.forEach((header) => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.service.sortColumn = column;
    this.service.sortDirection = direction;

  }

  onSelect({ selected }) {
    this.selected.splice(0, this.selected.length);
    this.selected.push(...selected);
  }
  item: any;

  ngOnInit() {
    this.retrieveDeliveryList();
  }
  retrieveDeliveryList(){
    this.deliveryService.retrieveDeliveryList().subscribe((response) => {
      this.deliveryList = response;
    });
  }
  changeStatusToDelivered(id){
    this.deliveryService.changeStatusToDelivered(id).subscribe(item => {
      this.item = item;
      this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        this.route.navigate(['pages/delivery-component']);
      });
    });


  }
}
