import {Component, OnInit} from '@angular/core';
import {NgForm, UntypedFormBuilder, UntypedFormGroup} from '@angular/forms';
import { Provider } from 'src/app/models/provider';
import {Product} from '../../../models/product';
import {ProviderService} from '../../../services/provider/provider.service';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-create-page',
  templateUrl: './create-page.component.html',
  styleUrls: ['./create-page.component.scss']
})
export class CreatePageComponent implements OnInit {
  public generalForm: UntypedFormGroup;
  public seoForm: UntypedFormGroup;
  public active = 1;
  provider: Provider = new Provider();

  constructor(private formBuilder: UntypedFormBuilder, private providerService: ProviderService, private route: ActivatedRoute) {
    this.createGeneralForm();
    this.createSeoForm();
  }

  createGeneralForm() {
    this.generalForm = this.formBuilder.group({
      name: [''],
      desc: [''],
      status: ['']
    });
  }
  createSeoForm() {
    this.seoForm = this.formBuilder.group({
      title: [''],
      keyword: [''],
      meta_desc: ['']
    });
  }

  ngOnInit() {
    this.provider = this.route.snapshot.data.provider;
  }

  onSubmit() {
    this.providerService.addProvider(this.provider).subscribe(
        (product: Product) => {
          console.log('Provider added successfully', product);
          // Reset the form
          this.provider = new Provider();
        },
        (error) => {
          console.error('Failed to add provider', error);
        }
    );
  }

}
