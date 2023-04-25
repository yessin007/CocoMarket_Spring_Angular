import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Provider} from "../../models/provider";
import { ProviderService } from '../provider/provider.service';
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProviderResolverService implements Resolve<Provider> {

  constructor(private providerService: ProviderService) { }
  provider: Provider = new Provider();
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Provider> {
    const id = route.paramMap.get('id');
    if (id){
      return this.providerService.editProvider(id);
    }else {
      return of(this.getProviderDetails());
    }
  }

  getProviderDetails(){
    return this.provider;
  }
}
