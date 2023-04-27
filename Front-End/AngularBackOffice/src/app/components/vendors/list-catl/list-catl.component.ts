import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {TableService} from '../../../shared/service/table.service';
import {ProviderService} from '../../../services/provider/provider.service';
import {Router} from '@angular/router';
import {LISTPAGEDB} from '../../../shared/tables/list-pages';
import {NgbdSortableHeader, SortEvent} from '../../../shared/directives/NgbdSortableHeader';
import {Observable} from 'rxjs';
import {Provider} from '../../../models/provider';
import {StoreCatalog} from '../../../models/storeCatalog';
import {CatalogServiceService} from '../../../services/catalogService/catalog-service.service';

@Component({
  selector: 'app-list-catl',
  templateUrl: './list-catl.component.html',
  styleUrls: ['./list-catl.component.scss']
})
export class ListCatlComponent implements OnInit{
  catalogList: StoreCatalog[] = [];

  constructor(private catalogservice : CatalogServiceService, private route: Router){}
  ngOnInit(): void {
    this.getAllCatalogs();

  }
  getAllCatalogs(){
    // tslint:disable-next-line:no-shadowed-variable
    this.catalogservice.getAllCatalog().subscribe((response) => {
      this.catalogList = response;
    });
  }
  deleteCatalog(id){
    this.catalogservice.deleteCatalog(id).subscribe((response) => {
      this.getAllCatalogs();
    });
  }
  editCatalog(id){
    this.route.navigate(['/vendors/create-storecatalog',  {id}]);
  }
}
