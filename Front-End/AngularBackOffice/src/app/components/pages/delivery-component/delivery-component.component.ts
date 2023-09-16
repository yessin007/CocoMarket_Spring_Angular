import {AfterViewInit, ChangeDetectorRef, Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {TableService} from '../../../shared/service/table.service';
import {DecimalPipe} from '@angular/common';
import {Observable} from 'rxjs';
import {LISTPAGEDB, ListPagesDB} from '../../../shared/tables/list-pages';
import {Delivery} from '../../../models/delivery';
import {ActivatedRoute, Router} from '@angular/router';
import {NgbdSortableHeader, SortEvent} from '../../../shared/directives/NgbdSortableHeader';
import {getGoogleMapsLink} from './mapLink';
import {DeliveriesService} from '../../../services/Deliveries-Service/deliveries-service.service';

@Component({
  selector: 'app-delivery-component',
  templateUrl: './delivery-component.component.html',
  styleUrls: ['./delivery-component.component.scss'],
  providers: [TableService, DecimalPipe]
})
export class DeliveryComponentComponent implements OnInit{

  constructor(public service: TableService, private deliveryService: DeliveriesService, private route: Router, private cdRef: ChangeDetectorRef) {
    this.tableItem$ = service.tableItem$;
    this.total$ = service.total$;
    this.service.setUserData(LISTPAGEDB);
  }
  public selected = [];
  public tableItem$: Observable<ListPagesDB[]>;
  public searchText;
  total$: Observable<number>;
  deliveryList: Delivery[] = [];
  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;
  item: any;
  i: any;
  googleMapsLink: string;

  getDeliveryById(id: number): Delivery {
    return this.deliveryList.find(delivery => delivery.id === id);
  }

  onClick(deliveryId: number) {
    const delivery = this.getDeliveryById(deliveryId);
    console.log('Client Latitude:', delivery.clientLatitude);
    console.log('Client Longitude:', delivery.clientLongitude);
    const link = getGoogleMapsLink(delivery.clientLatitude, delivery.clientLongitude);
    console.log('Google Maps Link:', link);
    window.open(link, '_blank');
  }


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

  ngOnInit() {
    this.retrieveDeliveryList();
    this.cdRef.detectChanges();

  }
  retrieveDeliveryList() {
    console.log('Before retrieving delivery list');
    this.deliveryService.retrieveDeliveryList().subscribe((response) => {
      console.log('Response received:', response);
      this.deliveryList = response;
    }, error => {
      console.error('Error occurred while retrieving delivery list:', error);
    }, () => {
      console.log('Delivery list retrieved successfully');
    });
  }

  changeStatusToDelivered(id){
    this.deliveryService.changeStatusToDelivered(id).subscribe(item => {
      this.item = item;
      console.log('Updated item:', this.item); // add console log here
      this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        this.route.navigate(['pages/delivery-component']);
      });
    });
  }

  dispatch(id){
    this.deliveryService.dispatch(id).subscribe(item => {
      this.item = item;
      console.log('Updated item:', this.item); // add console log here
      this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
        this.route.navigate(['pages/delivery-component']);
      });
    });
  }
}
