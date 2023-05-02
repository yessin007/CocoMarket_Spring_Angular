import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../auth.service';
import {Payement} from '../../models/Payement';
import {RequestBaseService} from '../request-base.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService extends RequestBaseService{
  readonly GET_ALL_PAYMENT = 'http://localhost:8089/radhwen/api/payement/retrive_all_payements';
  readonly DELETE_PAYMENT = 'http://localhost:8089/radhwen/api/payement/delete_payement/';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    super(auth, httpClient);

  }
  getAllPayments(){
    return this.httpClient.get<Payement[]>(this.GET_ALL_PAYMENT);
  }

  deletePayment(id){
    return this.httpClient.delete(this.DELETE_PAYMENT + id);
  }

}
