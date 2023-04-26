import { TestBed } from '@angular/core/testing';

import { OrderResolverServiceService } from './order-resolver-service.service';

describe('OrderResolverServiceService', () => {
  let service: OrderResolverServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderResolverServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
