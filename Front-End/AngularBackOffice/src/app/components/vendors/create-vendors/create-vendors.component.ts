import { Component, OnInit } from '@angular/core';
import {NgForm, UntypedFormBuilder, UntypedFormGroup} from '@angular/forms';
import {Store} from '../../../models/store';
import {StoreService} from '../../../services/store/store.service';
import {FileHandle} from '../../../models/FileHandle';
import {DomSanitizer} from '@angular/platform-browser';
import {ActivatedRoute} from '@angular/router';
import {User} from "../../../models/User";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-create-vendors',
  templateUrl: './create-vendors.component.html',
  styleUrls: ['./create-vendors.component.scss']
})
export class CreateVendorsComponent implements OnInit {
  public accountForm: UntypedFormGroup;
  public permissionForm: UntypedFormGroup;
  public active = 1;
  files: File[] = [];
  array: FileHandle[] = [];
  store: Store = new Store();
  // tslint:disable-next-line:new-parenssa
  currentUser: User = new User;
  // tslint:disable-next-line:ban-types
  protected currentToken!: String;
  constructor(private formBuilder: UntypedFormBuilder, private sanitizer: DomSanitizer, private storeService: StoreService, private activatedRoute: ActivatedRoute, private auth: AuthService) {
    this.createAccountForm();
    this.createPermissionForm();
    this.auth.currentUser.subscribe(data => {
      this.currentUser = data;
    });
    this.auth.currentToken.subscribe( data => {
      this.currentToken = data;
    });
  }
  onSelect(event) {
    console.log(event);
    // this.files.push(...event.addedFiles);
    if (event.addedFiles){
      const file = event.addedFiles[0];
      const reader = new FileReader();
      reader.readAsDataURL(file);
      const fileHandle: FileHandle = {
        filefile: file,
        url: this.sanitizer.bypassSecurityTrustUrl(
            window.URL.createObjectURL(file)
        ),
        preview: reader.result as string
      };
      this.array.push(fileHandle);
      this.store.storeImages = this.array;
      console.log(this.store.storeImages);
    }
  }
  onRemove(event) {
    console.log(event);
    this.array.splice(this.array.indexOf(event), 1);
  }
  createAccountForm() {
    this.accountForm = this.formBuilder.group({
      fname: [''],
      lname: [''],
      email: [''],
      password: [''],
      confirmPwd: ['']
    });
  }
  createPermissionForm() {
    this.permissionForm = this.formBuilder.group({
    });
  }


  ngOnInit() {

    this.store = this.activatedRoute.snapshot.data.store;
    console.log(this.store);
  }
  onSubmit(storeForm: NgForm) {
    console.log(this.currentToken);

    const storeFormData = this.prepareFormData(this.store);
    this.storeService.addStore(storeFormData).subscribe(
        (store: Store) => {
          console.log('Store added successfully', store);
          // Reset the form
          this.store = new Store();
        },
        (error) => {
          console.error('Failed to add store', error);
        }
    );
    this.array = [];
  }
  prepareFormData(store: Store): FormData{
    const formData = new FormData();

    formData.append('store', new Blob([JSON.stringify(store)], {type: 'application/json'}));
    if (store.storeImages && store.storeImages.length) {
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < store.storeImages.length; i++) {
        formData.append('imageFile', store.storeImages[i].filefile, store.storeImages[i].filefile.name);
      }
    }
    return formData;
  }
}
