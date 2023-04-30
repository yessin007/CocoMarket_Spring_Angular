import { TestBed } from '@angular/core/testing';

import { DeliveriesService } from './deliveries.service';

describe('DeliveriesService', () => {
  let service: DeliveriesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeliveriesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
