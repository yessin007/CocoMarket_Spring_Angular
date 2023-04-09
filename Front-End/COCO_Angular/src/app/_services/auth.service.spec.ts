import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';
import {HttpHeaders} from "@angular/common/http";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

describe('AuthService', () => {
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
