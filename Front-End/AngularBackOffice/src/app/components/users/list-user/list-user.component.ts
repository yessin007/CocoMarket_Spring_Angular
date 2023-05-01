import { DecimalPipe } from '@angular/common';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import {Observable, of} from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { NgbdSortableHeader, SortEvent } from 'src/app/shared/directives/NgbdSortableHeader';
import { TableService } from 'src/app/shared/service/table.service';
import { UserListDB, USERLISTDB } from 'src/app/shared/tables/list-users';
import {UserService} from "../../../shared/service/userService";
import {User} from "../../../models/User";

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.scss'],
  providers: [TableService, DecimalPipe]
})
export class ListUserComponent implements OnInit {
  public user_list = []

  public tableItem$: Observable<User[]>;
  public searchText;
  total$: Observable<number>;

	constructor(public service: TableService, private auth: AuthService, private userService: UserService) {

		//this.total$ = service.total$;
			/*this.userService.getAllUsers().then(res => {
				console.log(res);
				this.tableItem$ = res.data;
			});*/

		this.getUsers();
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

  getUsers(){
	  this.userService.getAllUsers().subscribe(users => {
		  this.tableItem$ = of(users);
		  this.service.setUserData(this.tableItem$);
	  });
  }

  ngOnInit() {
  }

	deleteUser(id: number) {
		this.userService.deleteUser(id).subscribe(res => {
			console.log(res);
		});
		this.getUsers();
		window.location.reload();
	}


}

