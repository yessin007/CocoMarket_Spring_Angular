import { DecimalPipe } from '@angular/common';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Observable } from 'rxjs';
import { NgbdSortableHeader, SortEvent } from 'src/app/shared/directives/NgbdSortableHeader';
import { TableService } from 'src/app/shared/service/table.service';
import { LISTPAGEDB, ListPagesDB } from 'src/app/shared/tables/list-pages';
import {ProviderService} from "../../../services/provider/provider.service";
import {Provider} from "../../../models/provider";
import {response} from "express";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-page',
  templateUrl: './list-page.component.html',
  styleUrls: ['./list-page.component.scss'],
  providers: [TableService, DecimalPipe]
})
export class ListPageComponent implements OnInit {
  public selected = [];
  public tableItem$: Observable<ListPagesDB[]>;
  public searchText;
  total$: Observable<number>;
  providerList: Provider[] = [];
  

  constructor(public service: TableService, private providerService: ProviderService, private route: Router) {
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

  ngOnInit() {
    this.getAllProviders();
  }
  getAllProviders(){
    // tslint:disable-next-line:no-shadowed-variable
      this.providerService.getAllProvider().subscribe((response) => {
      this.providerList = response;
    });
  }

  deleteProvider(id){
    this.providerService.deleteProvider(id).subscribe((response) => {
      this.getAllProviders();
    });
  }
  
  editProvider(id){
    this.route.navigate(['/pages/create-page',  {id}]);
  }

  setLatLngToProvider(id) {
    this.providerService.setLatLngToProvider(id).subscribe();
  }

}
