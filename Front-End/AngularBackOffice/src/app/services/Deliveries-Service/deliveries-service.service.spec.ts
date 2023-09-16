import { TestBed } from '@angular/core/testing';

import { DeliveriesServiceService } from './deliveries-service.service';

describe('DeliveriesServiceService', () => {
  let service: DeliveriesServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeliveriesServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
